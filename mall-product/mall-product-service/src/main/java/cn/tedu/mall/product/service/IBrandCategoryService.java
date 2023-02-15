package cn.tedu.mall.product.service;

/**
 * <p>品牌类别关联数据的业务接口</p>
 *
 * @author java.tedu.cn
 * @since 2021-11-30
 */
public interface IBrandCategoryService {

    /**
     * 绑定品牌与商品类别
     *
     * @param brandId    品牌id
     * @param categoryId 商品类别id
     */
    void bindBrandAndCategory(Long brandId, Long categoryId);

    /**
     * 解绑品牌与商品类别
     *
     * @param brandId    品牌id
     * @param categoryId 商品类别id
     */
    void unbindBrandAndCategory(Long brandId, Long categoryId);

}
