package cn.tedu.mall.product.mapper;

import cn.tedu.mall.pojo.product.model.Category;
import cn.tedu.mall.pojo.product.vo.CategoryStandardVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>类别Mapper接口</p>
 *
 * @author tedu.cn
 * @since 2021-11-30
 */
@Repository
public interface CategoryMapper {

    /**
     * 新增类别
     *
     * @param category 新增的类别对象
     * @return 受影响的行数
     */
    int insert(Category category);

    /**
     * 根据id删除类别
     *
     * @param id 被删除的类别的id
     * @return 受影响的行数
     */
    int deleteById(Long id);

    /**
     * 设置将指定类别的"是否包含子级类别"的值
     *
     * @param id     需要将"是否包含子级类别"设置为真（1）的类别的id
     * @param parent 是否为父级（是否包含子级），1=是父级，0=不是父级
     * @return 受影响的行数
     */
    int updateParentById(@Param("id") Long id, @Param("parent") Integer parent);

    /**
     * 设置将指定类别的"是否启用"的值
     *
     * @param id     需要将"是否包含子级类别"设置为真（1）的类别的id
     * @param enable 是否启用，1=启用，0=未启用
     * @return 受影响的行数
     */
    int updateEnableById(@Param("id") Long id, @Param("enable") Integer enable);

    /**
     * 设置将指定类别的"是否显示在导航栏中"的值
     *
     * @param id        需要将"是否包含子级类别"设置为真（1）的类别的id
     * @param display 是否显示在导航栏中，1=启用，0=未启用
     * @return 受影响的行数
     */
    int updateDisplayById(@Param("id") Long id, @Param("display") Integer display);

    /**
     * 修改类别的基本信息
     *
     * @param category 封装了被修改的类别的id，和新的相关值的对象
     * @return 受影响的行数
     */
    int updateBaseInfoById(Category category);

    /**
     * 修改类别的全部信息
     *
     * @param category 封装了被修改的类别的id，和新的相关值的对象
     * @return 受影响的行数
     */
    int updateFullInfoById(Category category);

    /**
     * 根据类别名称统计数量
     *
     * @param name 类别名称
     * @return 此名称的类别的数量
     */
    @Deprecated
    int countByName(String name);

    /**
     * 根据父级类别id统计其子级类别数量
     *
     * @param parentId 父级类别id
     * @return 子级类别数量
     */
    int countByParentId(Long parentId);

    /**
     * 根据类别id查询类别详情
     *
     * @param id 类别id
     * @return 匹配的类别详情，如果没有匹配的数据，则返回null
     */
    CategoryStandardVO getById(Long id);

    /**
     * 根据类别名称查询类别详情
     *
     * @param name 类别名称
     * @return 匹配的类别详情，如果没有匹配的数据，则返回null
     */
    CategoryStandardVO getByName(String name);

    /**
     * 查询类别列表
     *
     * @return 类别列表，如果没有匹配的数据，将返回长度为0的列表
     */
    List<CategoryStandardVO> list();

    /**
     * 根据品牌id查询类别列表
     *
     * @param brandId 品牌id
     * @return 类别列表，如果没有匹配的数据，将返回长度为0的列表
     */
    List<CategoryStandardVO> listByBrandId(Long brandId);

    /**
     * 根据父级id查询类别列表
     *
     * @param parentId 父级id
     * @return 类别列表，如果没有匹配的数据，将返回长度为0的列表
     */
    List<CategoryStandardVO> listByParentId(Long parentId);

    /**
     * 全量查询分类列表
     * @return
     */
    List<CategoryStandardVO> selectAllCategories();
}
