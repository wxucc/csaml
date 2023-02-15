package cn.tedu.mall.seckill.service;

import cn.tedu.mall.pojo.seckill.vo.SeckillSkuVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tedu.cn
 * @since 2022-02-23
 */
public interface ISeckillSkuService {

    List<SeckillSkuVO> listSeckillSkus(Long spuId);
}
