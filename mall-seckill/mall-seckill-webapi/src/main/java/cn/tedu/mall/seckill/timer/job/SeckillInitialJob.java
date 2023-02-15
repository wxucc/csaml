package cn.tedu.mall.seckill.timer.job;

import cn.tedu.mall.common.config.PrefixConfiguration;
import cn.tedu.mall.pojo.seckill.model.SeckillSku;
import cn.tedu.mall.pojo.seckill.model.SeckillSpu;
import cn.tedu.mall.seckill.mapper.SeckillSkuMapper;
import cn.tedu.mall.seckill.mapper.SeckillSpuMapper;
import cn.tedu.mall.seckill.utils.SeckillCacheUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.RandomUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
public class SeckillInitialJob implements Job {

    // 查询spu相关信息的mapper
    @Autowired
    private SeckillSpuMapper spuMapper;
    // 查询sku相关信息的mapper
    @Autowired
    private SeckillSkuMapper skuMapper;
    // 操作Redis的对象
    @Autowired
    private RedisTemplate redisTemplate;

    /*
    RedisTemplate对象在保存数据到Redis时,会将数据进行序列化后保存
    这样做,对java对象或类似的数据再Redis中的读写效率是高的,但缺点是不能再redis中对数据进行修改
    要想修改,必须从redis中获取后修改属性,在添加\覆盖到Redis中,这样的操作在多线程时就容易产生线程安全问题
    我们现在保存的库存数,如果也用redisTemplate保存,高并发时就会产生超卖
    解决办法是操作一个能够直接在Redis中对数据进行修改的对象,来保存它的库存数,防止超卖

    SpringDataRedis提供了StringRedisTemplate类型,它可以直接操作Redis中的字符串值
    使用StringRedisTemplate向Redis保存数据,可以直接保存字符串,没有序列化过程的
    它支持使用java代码直接向redis发送修改库存数值的方法,适合当下管理库存的业务需求
    最后结合Redis操作数据是单线程的特征,避免线程安全问题,防止超卖

     */
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    // 在秒杀开始前5分钟,进行秒杀信息的预热工作,将秒杀过程中的热点数据保存到Redis
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        // 先将5分钟后要开始进行秒杀的商品信息查询出来,所以要先获得一个5分钟后的时间对象
        LocalDateTime time=LocalDateTime.now().plusMinutes(5);
        // 查询这个时间进行秒杀的商品列表
        List<SeckillSpu> seckillSpus = spuMapper.findSeckillSpusByTime(time);
        // 遍历查询出的所有商品集合
        for (SeckillSpu spu : seckillSpus){
            // 要预热当前spu对应的所有秒杀sku中的库存数到Redis
            // 所以要根据spuId查询出它对应的sku列表
            List<SeckillSku> seckillSkus =
                            skuMapper.findSeckillSkusBySpuId(spu.getSpuId());
            // 再遍历seckillSkus集合,获取库存数
            for(SeckillSku sku: seckillSkus){
                log.info("开始将{}号sku商品的库存数预热到redis",sku.getSkuId());
                // 要想将库存数保存到Redis,先确定使用的key
                // SeckillCacheUtils.getStockKey是获取库存字符串常量的方法
                // 方法参数传入sku.getSkuId(),会追加在字符串常量之后
                // 最终skuStockKey的实际值可能是:   mall:seckill:sku:stock:1
                String skuStockKey=SeckillCacheUtils.getStockKey(sku.getSkuId());
                // 获取了key之后,检查Redis中是否已经包含这个key
                if(redisTemplate.hasKey(skuStockKey)){
                    // 如果这个Key已经存在了,证明之前已经完成了缓存,直接跳过即可
                    log.info("{}号sku的库存数已经缓存过了",sku.getSkuId());
                }else{
                    stringRedisTemplate.boundValueOps(skuStockKey).set(
                            sku.getSeckillStock()+"",
                            // 秒杀时间+提前的5分钟+防雪崩随机数(30秒)
                            // 1000*60*60*2+1000*60*5+ RandomUtils.nextInt(30000),
                            1000*60*5+RandomUtils.nextInt(30000),
                            TimeUnit.MILLISECONDS);
                    log.info("{}号sku商品库存成功预热到Redis!",sku.getSkuId());
                }
            }
            // 在内层循环结束后,外层循环结束前,编写spu对应的随机码的预热
            // 随机码就是一个随机数,随机范围自定即可
            // 随机生成后保存到Redis中即可,使用方式后面会讲
            // 随机码key  mall:seckill:spu:url:rand:code:2
            String randCodeKey=SeckillCacheUtils.getRandCodeKey(spu.getSpuId());
            // 判断当前随机码key是否已经存在
            if (redisTemplate.hasKey(randCodeKey)){
                // 如果已经存在了,不需要任何其它操作
                // 为了方法今后的测试,我们取出这个随机码输出到控制台用于测试
                int randCode=(int)redisTemplate.boundValueOps(randCodeKey).get();
                log.info("{}号spu的商品随机码已经缓存了,值为:{}",spu.getSpuId(),randCode);
            }else{
                // 如果不存在,生成随机码
                // 生成的随机码这里范围定为1000000~9999999
                int randCode=RandomUtils.nextInt(9000000)+1000000;
                // 将生成的随机码保存到redis中
                redisTemplate.boundValueOps(randCodeKey).set(
                        randCode,
                        1000*60*5+RandomUtils.nextInt(30000),
                        TimeUnit.MILLISECONDS);
                log.info("{}号spu的随机码预热完成!值为:{}",spu.getSpuId(),randCode);
            }
        }
    }
}
