package cn.tedu.mall.product.mapper;

import cn.tedu.mall.pojo.product.model.Spu;
import cn.tedu.mall.pojo.product.query.SpuQuery;
import cn.tedu.mall.pojo.product.vo.SpuListItemVO;
import cn.tedu.mall.pojo.product.vo.SpuStandardVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>SPU（Standard Product Unit）Mapper接口</p>
 *
 * @author tedu.cn
 * @since 2021-11-30
 */
@Repository
public interface SpuMapper {

    /**
     * 新增SPU
     *
     * @param spu 新增的SPU对象
     * @return 受影响的行数
     */
    int insert(Spu spu);

    /**
     * 根据id删除SPU
     *
     * @param id 被删除的SPU的id
     * @return 受影响的行数
     */
    int deleteById(Long id);

    /**
     * 修改SPU
     *
     * @param spu 封装了被修改的SPU的id，和新的相关值的对象
     * @return 受影响的行数
     */
    int update(Spu spu);

    /**
     * 修改SPU的审核状态
     *
     * @param id      SPU id
     * @param checked 新的审核状态
     * @return 受影响的行数
     */
    int updateCheckedById(@Param("id") Long id, @Param("checked") Integer checked);

    /**
     * 更新价格
     *
     * @param id    SPU id
     * @param price 新的价值
     * @return 受影响的行数
     */
    int updatePriceById(@Param("id") Long id, @Param("price") BigDecimal price);

    /**
     * 更新库存
     *
     * @param id    SPU id
     * @param stock 新的库存值
     * @return 受影响的行数
     */
    int updateStockById(@Param("id") Long id, @Param("stock") Integer stock);

    /**
     * 根据相册id统计SPU数量
     *
     * @param albumId 相册id
     * @return 该相册的SPU数量，如果此相册未关联SPU，则返回0
     */
    int countByAlbumId(Long albumId);

    /**
     * 根据品牌id统计SPU数量
     *
     * @param brandId 品牌id
     * @return 该品牌的SPU数量，如果此品牌下无SPU，则返回0
     */
    int countByBrandId(Long brandId);

    /**
     * 根据SPU编号统计SPU数量
     *
     * @param typeNumber SPU编号
     * @return 该编号对应的SPU数量，如果此编号无对应的SPU，则返回0
     */
    int countByTypeNumber(String typeNumber);

    /**
     * 根据SPU id查询SPU详情
     *
     * @param id SPU id
     * @return 匹配的SPU详情，如果没有匹配的数据，则返回null
     */
    SpuStandardVO getById(Long id);

    /**
     * 查询SPU列表
     *
     * @return SPU列表，如果没有匹配的数据，将返回长度为0的列表
     */
    List<SpuListItemVO> list();

    /**
     * 查询SPU列表
     *
     * @param spuQuery 封装了查询条件的对象
     * @return SPU列表，如果没有匹配的数据，将返回长度为0的列表
     */
    List<SpuListItemVO> listByCustomCondition(SpuQuery spuQuery);


    // 全查所有spu
    @Select("select * from pms_spu")
    List<Spu> findAllList();


}
