package cn.tedu.mall.product.mapper;

import cn.tedu.mall.pojo.product.model.AttributeTemplate;
import cn.tedu.mall.pojo.product.vo.AttributeTemplateDetailsVO;
import cn.tedu.mall.pojo.product.vo.AttributeTemplateListItemVO;
import cn.tedu.mall.pojo.product.vo.AttributeTemplateStandardVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>属性模板Mapper接口</p>
 *
 * @author tedu.cn
 * @since 2021-11-30
 */
@Repository
public interface AttributeTemplateMapper {

    /**
     * 新增属性模板
     *
     * @param attributeTemplate 新增的属性模板对象
     * @return 受影响的行数
     */
    int insert(AttributeTemplate attributeTemplate);

    /**
     * 根据id删除属性模板
     *
     * @param id 被删除的属性模板的id
     * @return 受影响的行数
     */
    int deleteById(Long id);

    /**
     * 修改属性模板
     *
     * @param attributeTemplate 封装了被修改的属性模板的id，和新的相关值的对象
     * @return 受影响的行数
     */
    int update(AttributeTemplate attributeTemplate);

    /**
     * 统计某名称的属性模板数量
     *
     * @param name 属性模板的名称
     * @return 匹配名称的属性模板数量
     */
    @Deprecated
    int countByName(String name);

    /**
     * 根据属性模板id查询属性模板详情
     *
     * @param id 属性模板id
     * @return 匹配的属性模板详情，如果没有匹配的数据，则返回null
     */
    AttributeTemplateStandardVO getById(Long id);

    /**
     * 根据属性模板名称查询属性模板详情
     *
     * @param name 属性模板名称
     * @return 匹配的属性模板详情，如果没有匹配的数据，则返回null
     */
    AttributeTemplateStandardVO getByName(String name);

    /**
     * 根据属性模板id查询属性模板详情（含关联表数据）
     *
     * @param id 属性模板id
     * @return 匹配的属性模板详情，如果没有匹配的数据，则返回null
     */
    AttributeTemplateDetailsVO getDetailsById(Long id);

    /**
     * 根据属性模板id及属性类型，查询属性模板详情
     *
     * @param id            属性模板id
     * @param attributeType 属性的类型
     * @return 匹配的属性模板详情，如果没有匹配的数据，则返回null
     */
    AttributeTemplateDetailsVO getDetailsByIdAndAttributeType(@Param("id") Long id, @Param("attributeType") Integer attributeType);

    /**
     * 根据属性模板id查询列表
     *
     * @return 属性模板列表，如果没有匹配的数据，将返回长度为0的列表
     */
    List<AttributeTemplateStandardVO> list();

    /**
     * 根据类别id查询属性模板列表
     *
     * @param categoryId 类别id
     * @return 属性模板列表，如果没有匹配的数据，将返回长度为0的列表
     */
    List<AttributeTemplateListItemVO> listByCategoryId(Long categoryId);

}
