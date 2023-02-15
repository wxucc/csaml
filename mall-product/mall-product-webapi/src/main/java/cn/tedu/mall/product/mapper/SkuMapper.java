package cn.tedu.mall.product.mapper;

import cn.tedu.mall.pojo.product.model.Sku;
import cn.tedu.mall.pojo.product.vo.SkuStandardVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>SKU（Stock Keeping Unit）Mapper接口</p>
 *
 * @author tedu.cn
 * @since 2021-11-30
 */
@Repository
public interface SkuMapper {

    /**
     * 新增SKU
     *
     * @param sku 新增的SKU对象
     * @return 受影响的行数
     */
    int insert(Sku sku);

    /**
     * 批量新增SKU
     *
     * @param skuList 新增的SKU集合
     * @return 受影响的行数
     */
    int insertBatch(List<Sku> skuList);

    /**
     * 根据id删除SKU
     *
     * @param id 被删除的SKU的id
     * @return 受影响的行数
     */
    int deleteById(Long id);

    /**
     * 修改SKU
     *
     * @param sku 封装了被修改的SKU的id，和新的相关值的对象
     * @return 受影响的行数
     */
    int updateFullInfoById(Sku sku);

    /**
     * 根据SPU id对所有相关库存进行求和
     *
     * @param spuId SPU id
     * @return 受影响的行数
     */
    int sumStockBySpuId(Long spuId);

    /**
     * 根据SPU id对所有相关价格求最小值
     *
     * @param spuId SPU id
     * @return 受影响的行数
     */
    BigDecimal getMinPriceBySpuId(Long spuId);

    /**
     * 根据SKU id查询SKU详情
     *
     * @param id SKU id
     * @return 匹配的SKU详情，如果没有匹配的数据，则返回null
     */
    SkuStandardVO getById(Long id);

    /**
     * 根据SPU id查询SKU列表
     *
     * @param spuId SPU id
     * @return SKU列表，如果没有匹配的数据，将返回长度为0的列表
     */
    List<SkuStandardVO> listBySpuId(Long spuId);

    /**
     * 根据skuId 更新对应库存
     * @param skuId
     * @param quantity
     * @return
     */
    int updateStockById(@Param("id")Long skuId, @Param("stock")Integer quantity);
}
