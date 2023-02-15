package cn.tedu.mall.product.mapper;

import cn.tedu.mall.pojo.product.model.Attribute;
import cn.tedu.mall.pojo.product.vo.AttributeDetailsVO;
import cn.tedu.mall.pojo.product.vo.AttributeStandardVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>属性Mapper接口</p>
 *
 * @author tedu.cn
 * @since 2021-11-30
 */
@Repository
public interface AttributeMapper {

    /**
     * 新增属性
     *
     * @param attribute 新增的属性对象
     * @return 受影响的行数
     */
    int insert(Attribute attribute);

    /**
     * 根据id删除属性
     *
     * @param id 被删除的属性的id
     * @return 受影响的行数
     */
    int deleteById(Long id);

    /**
     * 修改属性
     *
     * @param attribute 封装了被修改的属性的id，和新的相关值的对象
     * @return 受影响的行数
     */
    int update(Attribute attribute);

    /**
     * 根据属性模板id统计商品属性的数量
     *
     * @param templateId 属性模板id
     * @return 此属性模板id下的商品属性的数量
     */
    int countByTemplateId(Long templateId);

    /**
     * 根据属性id查询属性简单信息
     *
     * @param id 属性id
     * @return 匹配的属性简单信息，如果没有匹配的数据，则返回null
     */
    AttributeStandardVO getById(Long id);

    /**
     * 根据属性id查询属性详细信息（含所属模板详情）
     *
     * @param id 属性id
     * @return 匹配的属性详细信息，如果没有匹配的数据，则返回null
     */
    AttributeDetailsVO getDetailsById(Long id);

    /**
     * 根据属性模板id查询列表
     *
     * @param templateId 属性模板id
     * @return 属性列表，如果没有匹配的数据，将返回长度为0的列表
     */
    List<AttributeStandardVO> listByTemplateId(Long templateId);

    /**
     * 根据属性模板id查询列表
     *
     * @param templateId 属性模板id
     * @param type       属性类型，1=销售属性，0=非销售属性
     * @return 属性列表，如果没有匹配的数据，将返回长度为0的列表
     */
    List<AttributeStandardVO> listByTemplateIdAndType(@Param("templateId") Long templateId, @Param("type") Integer type);

    /**
     * 根据spuId 查询关联表格的数据获取对应所有attribute属性信息
     *
     * @param spuId
     * @return
     */
    List<AttributeStandardVO> selectAttributesBySpuId(Long spuId);
}