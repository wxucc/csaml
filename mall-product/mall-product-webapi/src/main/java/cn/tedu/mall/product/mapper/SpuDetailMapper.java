package cn.tedu.mall.product.mapper;

import cn.tedu.mall.pojo.product.model.SpuDetail;
import cn.tedu.mall.pojo.product.vo.SpuDetailStandardVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>SPU详情Mapper接口</p>
 *
 * @author tedu.cn
 * @since 2021-11-30
 */
@Repository
public interface SpuDetailMapper {

    /**
     * 新增SPU详情
     *
     * @param spuDetail 新增的SPU详情对象
     * @return 受影响的行数
     */
    int insert(SpuDetail spuDetail);

    /**
     * 根据Spu id删除Spu
     *
     * @param spuId 被删除的Spu的id
     * @return 受影响的行数
     */
    int deleteBySpuId(Long spuId);

    /**
     * 修改SPU详情
     *
     * @param spuId  SPU id
     * @param detail 封新的SPU详情
     * @return 受影响的行数
     */
    int updateDetailBySpuId(@Param("spuId") Long spuId, @Param("detail") String detail);

    /**
     * 根据Spu id查询Spu详情
     *
     * @param spuId Spu id
     * @return 匹配的Spu详情，如果没有匹配的数据，则返回null
     */
    SpuDetailStandardVO getBySpuId(Long spuId);

}
