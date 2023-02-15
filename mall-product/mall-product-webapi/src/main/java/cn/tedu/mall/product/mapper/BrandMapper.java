package cn.tedu.mall.product.mapper;

import cn.tedu.mall.pojo.product.model.Brand;
import cn.tedu.mall.pojo.product.vo.BrandStandardVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>品牌Mapper接口</p>
 *
 * @author tedu.cn
 * @since 2021-11-30
 */
@Repository
public interface BrandMapper {

    /**
     * 新增品牌
     *
     * @param brand 新增的品牌对象
     * @return 受影响的行数
     */
    int insert(Brand brand);

    /**
     * 根据id删除品牌
     *
     * @param id 被删除的品牌的id
     * @return 受影响的行数
     */
    int deleteById(Long id);

    /**
     * 修改属性
     *
     * @param brand 封装了被修改的属性的id，和新的相关值的对象
     * @return 受影响的行数
     */
    int updateFullInfoById(Brand brand);

    /**
     * 根据品牌id查询品牌详情
     *
     * @param id 品牌id
     * @return 匹配的品牌详情，如果没有匹配的数据，则返回null
     */
    BrandStandardVO getById(Long id);

    /**
     * 根据品牌名称查询品牌详情
     *
     * @param name 品牌名称
     * @return 匹配的品牌详情，如果没有匹配的数据，则返回null
     */
    BrandStandardVO getByName(String name);

    /**
     * 查询品牌列表
     *
     * @return 品牌列表，如果没有匹配的数据，将返回长度为0的列表
     */
    List<BrandStandardVO> list();

    /**
     * 根据分类id查询品牌列表
     *
     * @param categoryId 分类id
     * @return 品牌列表，如果没有匹配的数据，将返回长度为0的列表
     */
    List<BrandStandardVO> listByCategoryId(Long categoryId);

}
