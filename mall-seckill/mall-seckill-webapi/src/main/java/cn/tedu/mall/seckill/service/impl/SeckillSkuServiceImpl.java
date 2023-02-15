package cn.tedu.mall.seckill.service.impl;

import cn.tedu.mall.pojo.product.vo.SkuStandardVO;
import cn.tedu.mall.pojo.seckill.model.SeckillSku;
import cn.tedu.mall.pojo.seckill.vo.SeckillSkuVO;
import cn.tedu.mall.product.service.seckill.IForSeckillSkuService;
import cn.tedu.mall.seckill.mapper.SeckillSkuMapper;
import cn.tedu.mall.seckill.service.ISeckillSkuService;
import cn.tedu.mall.seckill.utils.SeckillCacheUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class SeckillSkuServiceImpl implements ISeckillSkuService {
    @Autowired
    private SeckillSkuMapper skuMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    // sku常规信息的查询还是dubbo调用product模块获取
    @DubboReference
    private IForSeckillSkuService dubboSkuService;

    @Override
    public List<SeckillSkuVO> listSeckillSkus(Long spuId) {
        // 执行查询, 根据spuId查询sku列表
        List<SeckillSku> seckillSkus=skuMapper.findSeckillSkusBySpuId(spuId);
        // 上面查询返回值泛型为SeckillSku,是秒杀信息集合
        // 当前方法的返回值是SeckillSkuVO是包含秒杀信息和常规信息的对象
        // 我们先实例化返回值类型泛型的集合,以备后续返回时使用
        List<SeckillSkuVO> seckillSkuVOs=new ArrayList<>();
        // 遍历秒杀信息集合对象
        for(SeckillSku sku : seckillSkus){
            // 获取skuId后面会经常使用
            Long skuId=sku.getSkuId();
            // 获取sku对的key
            String skuVOKey= SeckillCacheUtils.getSeckillSkuVOKey(skuId);
            SeckillSkuVO seckillSkuVO=null;
            // 判断当前Redis中是否已经有这个key
            if(redisTemplate.hasKey(skuVOKey)){
                seckillSkuVO=(SeckillSkuVO)redisTemplate
                                 .boundValueOps(skuVOKey).get() ;
            }else{
                // 如果redis中存在这个key,就要查询数据库
                // dubbo调用查询sku常规信息
                SkuStandardVO skuStandardVO=dubboSkuService.getById(skuId);
                // 实例化SeckillSkuVO对象
                seckillSkuVO=new SeckillSkuVO();
                BeanUtils.copyProperties(skuStandardVO,seckillSkuVO);
                // 秒杀信息手动赋值
                seckillSkuVO.setSeckillPrice(sku.getSeckillPrice());
                seckillSkuVO.setStock(sku.getSeckillStock());
                seckillSkuVO.setSeckillLimit(sku.getSeckillLimit());
                // seckillSkuVO保存到Redis
                redisTemplate.boundValueOps(skuVOKey).set(
                       seckillSkuVO,
                        1000*60*5+ RandomUtils.nextInt(30000),
                        TimeUnit.MILLISECONDS);
            }
            // if-else结构结束后,seckillSkuVO是一定被赋值的(redis或数据库查询出的)
            // 要将它添加到seckillSkuVOs这个集合中
            seckillSkuVOs.add(seckillSkuVO);
        }
        // 返回集合!!!
        return seckillSkuVOs;
    }
}
