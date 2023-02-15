package cn.tedu.mall.product.mapper;

import cn.tedu.mall.pojo.product.model.SkuSpecification;
import cn.tedu.mall.pojo.product.vo.SkuSpecificationStandardVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>SKU属性数据Mapper接口</p>
 *
 * @author tedu.cn
 * @since 2021-11-30
 */
@Repository
public interface SkuSpecificationMapper {

    /**
     * 批量新增SKU属性数据
     *
     * @param skuSpecifications 新增的SKU属性数据集合
     * @return 受影响的行数
     */
    int insertBatch(List<SkuSpecification> skuSpecifications);

    /**
     * 根据id删除SKU属性数据
     *
     * @param id 被删除的SKU属性数据的id
     * @return 受影响的行数
     */
    int deleteById(Long id);

    /**
     * 根据SKU属性数据查询详情
     *
     * @param id SKU属性数据id
     * @return 匹配的SKU属性数据详情，如果没有匹配的数据，则返回null
     */
    SkuSpecificationStandardVO getById(Long id);

}
