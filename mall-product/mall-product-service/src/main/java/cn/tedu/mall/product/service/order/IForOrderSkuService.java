package cn.tedu.mall.product.service.order;

import cn.tedu.mall.pojo.product.vo.SkuStandardVO;

public interface IForOrderSkuService {

    int reduceStockNum(Long skuId, Integer quantity);
    /**
     * 根据SKU id查询SKU详情
     *
     * @param id SKU id
     * @return 匹配的SKU详情，如果没有匹配的数据，则返回null
     */
    SkuStandardVO getById(Long id);
}
