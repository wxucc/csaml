package cn.tedu.mall.product.mapper;

import cn.tedu.mall.pojo.product.model.BrandCategory;
import cn.tedu.mall.pojo.product.vo.BrandCategoryStandardVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>品牌类别关联Mapper接口</p>
 *
 * @author tedu.cn
 * @since 2021-11-30
 */
@Repository
public interface BrandCategoryMapper {

    /**
     * 新增品牌与分类的绑定
     *
     * @param brandCategory 新增的品牌与分类的绑定对象
     * @return 受影响的行数
     */
    int insert(BrandCategory brandCategory);

    /**
     * 根据品牌与分类删除数据，即解除(删除)绑定关系
     *
     * @param brandId    品牌id
     * @param categoryId 分类id
     * @return 受影响的行数
     */
    int deleteByBrandIdAndCategoryId(@Param("brandId") Long brandId, @Param("categoryId") Long categoryId);

    /**
     * 统计品牌与分类的绑定数量
     *
     * @param brandId    品牌id
     * @param categoryId 分类id
     * @return 匹配的品牌与分类的绑定数量
     */
    int countByBrandIdAndCategoryId(@Param("brandId") Long brandId, @Param("categoryId") Long categoryId);

    /**
     * 查询所有品牌与类别的绑定关系列表
     *
     * @return 品牌与类别的绑定关系列表，如果没有绑定关系，则返回长度为0的列表
     */
    List<BrandCategoryStandardVO> list();

    /**
     * 查询指定品牌的绑定关系列表
     *
     * @param brandId 品牌id
     * @return 指定品牌的绑定关系列表，如果没有绑定关系，则返回长度为0的列表
     */
    List<BrandCategoryStandardVO> listByBrandId(Long brandId);

    /**
     * 查询指定类别的绑定关系列表
     *
     * @param categoryId 类别id
     * @return 指定类别的绑定关系列表，如果没有绑定关系，则返回长度为0的列表
     */
    List<BrandCategoryStandardVO> listByCategoryId(Long categoryId);

}
