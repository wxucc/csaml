package cn.tedu.mall.product.service.front;

import cn.tedu.mall.common.restful.JsonPage;
import cn.tedu.mall.pojo.product.vo.SkuStandardVO;

import java.util.List;

public interface IForFrontSkuService {
    /**
     * 利用spuId 获取sku列表集合
     * @param spuId
     * @return
     */
    List<SkuStandardVO> getSkusBySpuId(Long spuId);
}
