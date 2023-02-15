package cn.tedu.mall.seckill.service;

import cn.tedu.mall.common.restful.JsonPage;
import cn.tedu.mall.pojo.seckill.vo.SeckillSpuDetailSimpleVO;
import cn.tedu.mall.pojo.seckill.vo.SeckillSpuVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tedu.cn
 * @since 2022-02-23
 */
public interface ISeckillSpuService {

    JsonPage<SeckillSpuVO> listSeckillSpus(Integer page, Integer pageSize);

    SeckillSpuVO getSeckillSpu(Long spuId);

    SeckillSpuDetailSimpleVO getSeckillSpuDetail(Long spuId);
}
