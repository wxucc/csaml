package cn.tedu.mall.seckill.service.impl;

import cn.tedu.mall.common.exception.CoolSharkServiceException;
import cn.tedu.mall.common.restful.JsonPage;
import cn.tedu.mall.common.restful.ResponseCode;
import cn.tedu.mall.pojo.product.vo.SpuDetailStandardVO;
import cn.tedu.mall.pojo.product.vo.SpuStandardVO;
import cn.tedu.mall.pojo.seckill.model.SeckillSpu;
import cn.tedu.mall.pojo.seckill.vo.SeckillSpuDetailSimpleVO;
import cn.tedu.mall.pojo.seckill.vo.SeckillSpuVO;
import cn.tedu.mall.product.service.seckill.IForSeckillSpuService;
import cn.tedu.mall.seckill.mapper.SeckillSpuMapper;
import cn.tedu.mall.seckill.service.ISeckillSpuService;
import cn.tedu.mall.seckill.utils.RedisBloomUtils;
import cn.tedu.mall.seckill.utils.SeckillCacheUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.redisson.api.RSet;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class SeckillSpuServiceImpl implements ISeckillSpuService {

    // 装配查询秒杀表信息的Mapper
    @Autowired
    private SeckillSpuMapper seckillSpuMapper;
    @Autowired
    private RedisBloomUtils redisBloomUtils;
    // 秒杀列表信息中,返回值为SeckillSpuVO,这个类型包含了商品的常规信息和秒杀信息
    // 我们需要通过product模块才能查询pms数据库的商品常规信息,所有要Dubbo调用
    @DubboReference
    private IForSeckillSpuService dubboSeckillSpuService;


    // 分页查询秒杀商品列表
    // 返回值泛型:SeckillSpuVO,这个类型包含了商品的常规信息和秒杀信息
    @Override
    public JsonPage<SeckillSpuVO> listSeckillSpus(Integer page, Integer pageSize) {
        // 设置分页条件
        PageHelper.startPage(page,pageSize);
        // 执行查询
        List<SeckillSpu> seckillSpus=seckillSpuMapper.findSeckillSpus();
        // 先声明匹配返回值类型的泛型集合,以用于最后的返回
        List<SeckillSpuVO> seckillSpuVOs=new ArrayList<>();
        // 遍历seckillSpus(没有常规信息的集合)
        for(SeckillSpu seckillSpu : seckillSpus){
            // 取出当前商品的spuId
            Long spuId=seckillSpu.getSpuId();
            // 利用dubbo根据spuId查询商品常规信息
            SpuStandardVO standardVO = dubboSeckillSpuService.getSpuById(spuId);
            // 秒杀信息在seckillSpu对象中,常规信息在standardVO对象里
            // 先实例化SeckillSpuVO,然后向它赋值常规信息和秒杀信息
            SeckillSpuVO seckillSpuVO=new SeckillSpuVO();
            // 将常规信息中同名属性赋值到seckillSpuVO
            BeanUtils.copyProperties(standardVO,seckillSpuVO);
            // 秒杀信息单独手动赋值即可
            seckillSpuVO.setSeckillListPrice(seckillSpu.getListPrice());
            seckillSpuVO.setStartTime(seckillSpu.getStartTime());
            seckillSpuVO.setEndTime(seckillSpu.getEndTime());
            // 到此为止seckillSpuVO就赋值完成了
            // 将它添加到返回值的集合中
            seckillSpuVOs.add(seckillSpuVO);
        }
        // 最后别忘了返回
        return JsonPage.restPage(new PageInfo<>(seckillSpuVOs));
    }

    // 操作Redis的对象
    @Autowired
    private RedisTemplate redisTemplate;
    // 判断布隆过滤器中是否包含指定元素的对象
    @Autowired
    private RedisBloomUtils redisBloomUtils;

    @Override
    public SeckillSpuVO getSeckillSpu(Long spuId) {
        String bloomKey = SeckillCacheUtils.getBloomFilterKey(LocalDate.now());
        log.info("当前布隆过滤器中key");
        if (!redisBloomUtils.bfexists(bloomKey,spuId.toString())){
            //进入这个if表示当前商品id不在布隆过滤器中
            //防止缓存击穿,抛出异常
            throw new CoolSharkServiceException(
                    ResponseCode.NOT_FOUND,"您访问的商品不存在(布隆过滤器生效)"
            );
        }
        // 在后面完整版代码中,这里是要编写经过布隆过滤器判断的
        // 只有布隆过滤器中存在的id才能继续运行,否则发生异常
        // 获得布隆过滤器的key
        String bloomKey=SeckillCacheUtils.getBloomFilterKey(LocalDate.now());
        log.info("当前布隆过滤器中key为:{}",bloomKey);
        if(!redisBloomUtils.bfexists(bloomKey,spuId+"")){
            // 进入这个if表示当前商品id不在布隆过滤器中
            // 防止缓存穿透,抛出异常
            throw new CoolSharkServiceException(
                    ResponseCode.NOT_FOUND,"您访问的商品不存在(布隆过滤器生效)");
        }

        // 当前方法的返回值SeckillSpuVO又是既包含秒杀信息又包含常规信息的对象
        // 目标是查询两方面的信息
        // 先判断Redis中是否已经有这个对象,先获取key
        // spuVOKey =  "mall:seckill:spu:vo:2"
        String spuVOKey= SeckillCacheUtils.getSeckillSpuVOKey(spuId);
        // 可以在判断前先声明返回值类型,赋值null即可
        SeckillSpuVO seckillSpuVO=null;
        // 判断 spuVOKey 是否已经在Redis中
        if(redisTemplate.hasKey(spuVOKey)){
            // 如果Redis已经存在这个Key,直接获取用于返回即可
            seckillSpuVO=(SeckillSpuVO) redisTemplate
                                .boundValueOps(spuVOKey).get();
        }else{
            // 如果Redis不存在这个Key,就需要从数据库查询了
            SeckillSpu seckillSpu=seckillSpuMapper.findSeckillSpuById(spuId);
            // 判断一下这个seckillSpu是否为null(因为布隆过滤器有误判)
            if(seckillSpu==null){
                throw new CoolSharkServiceException(
                        ResponseCode.NOT_FOUND,"您访问的商品不存在");
            }
            SpuStandardVO spuStandardVO =
                    dubboSeckillSpuService.getSpuById(spuId);
            // 将秒杀信息和常规信息都赋值到seckillSpuVO对象
            // 要先实例化seckillSpuVO对象才能赋值,否则报空指针
            seckillSpuVO=new SeckillSpuVO();
            BeanUtils.copyProperties(spuStandardVO,seckillSpuVO);
            // 手动赋值秒杀信息
            seckillSpuVO.setSeckillListPrice(seckillSpu.getListPrice());
            seckillSpuVO.setStartTime(seckillSpu.getStartTime());
            seckillSpuVO.setEndTime(seckillSpu.getEndTime());
            // 将seckillSpuVO保存到Redis,以便后续请求直接从Redis中获取
            redisTemplate.boundValueOps(spuVOKey).set(
                    seckillSpuVO,
                    1000*60*5+ RandomUtils.nextInt(30000),
                    TimeUnit.MILLISECONDS);
        }
        // 到此为止,seckillSpuVO对象一定是除url之外所有属性都被赋值了
        // url属性的作用是发送给前端后,前端使用它来向后端发起秒杀订单请求的
        // 所以我们给url赋值,就相当于允许用户购买当前商品的许可
        // 要求判断当前时间是否在允许的秒杀时间范围内
        // 获取当前时间
        LocalDateTime nowTime=LocalDateTime.now();
        // 因为再次连接数据库会消耗更多时间,高并发程序要避免不必要的数据库连接
        // 我们从seckillSpuVO对象中获取开始和结束时间进行判断即可
        if (seckillSpuVO.getStartTime().isBefore(nowTime) &&
                nowTime.isBefore(seckillSpuVO.getEndTime())){
            // 表示当前时间在秒杀时间段内,可以为url赋值
            // 要获取redis中预热的随机码
            String randCodeKey=SeckillCacheUtils.getRandCodeKey(spuId);
            // 判断随机码的key是否在redis中
            if(!redisTemplate.hasKey(randCodeKey)){
               // 如果不存在,直接抛异常
               throw new CoolSharkServiceException(
                       ResponseCode.NOT_FOUND,"当前随机码不存在");
            }
            // 获取随机码
            String randCode=redisTemplate.boundValueOps(randCodeKey).get()+"";
            // 将随机码赋值到url
            seckillSpuVO.setUrl("/seckill/"+randCode);
            log.info("被赋值的url为:{}",seckillSpuVO.getUrl());
        }
        // 千万别忘了返回seckillSpuVO!!!
        return seckillSpuVO;
    }

    // 项目中没有定义SpuDetail的Key的常量,我们可以自己声明一个
    public static final String SECKILL_SPU_DETAIL_PREFIX="seckill:spu:detail:";

    // 根据spuId查询spuDetail
    @Override
    public SeckillSpuDetailSimpleVO getSeckillSpuDetail(Long spuId) {
        String spuDetailKey=SECKILL_SPU_DETAIL_PREFIX+spuId;
        // 声明一个返回值类型对象
        SeckillSpuDetailSimpleVO simpleVO=null;
        // 判断redis中是否包含这个key
        if(redisTemplate.hasKey(spuDetailKey)){
            // 如果redis中有这个key
            simpleVO=(SeckillSpuDetailSimpleVO)
                        redisTemplate.boundValueOps(spuDetailKey).get();
        }else{
            // 如果Redis中不存在这个key
            // 需要从数据库查询,利用dubbo查询product模块即可
            SpuDetailStandardVO spuDetailStandardVO =
                    dubboSeckillSpuService.getSpuDetailById(spuId);
            // 实例化simpleVO对象
            simpleVO=new SeckillSpuDetailSimpleVO();
            BeanUtils.copyProperties(spuDetailStandardVO,simpleVO);
            // 保存到Redis中
            redisTemplate.boundValueOps(spuDetailKey).set(
                    simpleVO,
                    1000*60*5+RandomUtils.nextInt(30000),
                    TimeUnit.MILLISECONDS);
        }
        // 返回simpleVO
        return simpleVO;
    }



}
