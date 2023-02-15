package cn.tedu.mall.seckill.timer.job;


import cn.tedu.mall.seckill.mapper.SeckillSpuMapper;
import cn.tedu.mall.seckill.utils.RedisBloomUtils;
import cn.tedu.mall.seckill.utils.SeckillCacheUtils;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

@Slf4j
public class SeckillBloomJob implements Job {

    //装配操作布隆过滤器的类
    @Autowired
    private RedisBloomUtils redisBloomUtils;

    //装配查询数据库中所有秒杀spuId的mapper
    @Autowired
    private SeckillSpuMapper seckillSpuMapper;


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //这个方法也是缓存预热,运行时机在秒杀开始前
        //将数据库中所有参与秒杀的商品查询出来直接查询数据库即可
        //先获得布隆过滤器的key
        String bloomKey = SeckillCacheUtils.getBloomFilterKey(LocalDate.now());
        Long[] spuIds = seckillSpuMapper.findAllSeckillSpuIds();
        //redisBloomUtils操作数组要求是字符串类型的,所以要转换以下
        String[] spuIdsStr = new String[spuIds.length];
        //遍历这个数组,将其中元素转换为String类型然后赋值到spuIdsStr数组中
        for (int i = 0; i < spuIds.length; i++){
            spuIdsStr[i] = spuIds[i].toString();
        }
        //将赋好值的数组添加到布隆过滤器
        redisBloomUtils.bfmadd(bloomKey,spuIdsStr);
    }
}
