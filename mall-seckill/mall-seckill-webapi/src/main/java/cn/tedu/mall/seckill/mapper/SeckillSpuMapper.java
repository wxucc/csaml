package cn.tedu.mall.seckill.mapper;

import cn.tedu.mall.pojo.seckill.model.SeckillSpu;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SeckillSpuMapper {

    // 查询秒杀商品列表的方法
    List<SeckillSpu> findSeckillSpus();

    // 根据给定时间,查询正在进行秒杀的商品
    List<SeckillSpu> findSeckillSpusByTime(LocalDateTime time);

    // 根据spuId查询秒杀spu信息
    SeckillSpu findSeckillSpuById(Long spuId);

    // 查询秒杀表中所有商品的spuId,由于后面保存到布隆过滤器防止缓存穿透
    Long[] findAllSeckillSpuIds();

}







