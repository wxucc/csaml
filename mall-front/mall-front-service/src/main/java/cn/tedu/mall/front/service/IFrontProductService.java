package cn.tedu.mall.front.service;

import cn.tedu.mall.common.restful.JsonPage;
import cn.tedu.mall.pojo.front.vo.FrontSpuDetailVO;
import cn.tedu.mall.pojo.product.vo.*;

import java.util.List;

/**
 * 商城前台商品相关业务层
 * @author xiaoxuwei
 * @version 1.0.0
 */
public interface IFrontProductService {
    /**
     * 根据分类id查询spu列表
     * @param categoryId
     * @param page
     * @param pageSize
     * @return
     */
    JsonPage<SpuListItemVO> listSpuByCategoryId(Long categoryId, Integer page, Integer pageSize);

    /**
     * 根据id查询spuvo对象
     * @param id
     */
    SpuStandardVO getFrontSpuById(Long id);

    List<SkuStandardVO> getFrontSkusBySpuId(Long spuId);

    /**
     * 利用spuId查询spu详情
     * @param spuId
     * @return
     */
    SpuDetailStandardVO getSpuDetail(Long spuId);

    /**
     * 微服务调用pms查询一个spu绑定的所有属性和值
     * @param spuId
     * @return
     */
    List<AttributeStandardVO> getSpuAttributesBySpuId(Long spuId);
}
