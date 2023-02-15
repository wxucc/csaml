package cn.tedu.mall.product.service.seckill;

import cn.tedu.mall.pojo.product.vo.SpuDetailStandardVO;
import cn.tedu.mall.pojo.product.vo.SpuStandardVO;

public interface IForSeckillSpuService {
    SpuStandardVO getSpuById(Long spuId);

    SpuDetailStandardVO getSpuDetailById(Long spuId);
}
