package cn.tedu.mall.product.service;

import cn.tedu.mall.common.restful.JsonPage;
import cn.tedu.mall.pojo.product.dto.AttributeTemplateAddNewDTO;
import cn.tedu.mall.pojo.product.dto.AttributeTemplateUpdateDTO;
import cn.tedu.mall.pojo.product.vo.AttributeTemplateDetailsVO;
import cn.tedu.mall.pojo.product.vo.AttributeTemplateListItemVO;
import cn.tedu.mall.pojo.product.vo.AttributeTemplateStandardVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>属性模板业务接口</p>
 *
 * @author java.tedu.cn
 * @since 2021-11-30
 */
public interface IAttributeTemplateService {

    /**
     * 增加商品属性模板
     *
     * @param attributeTemplateAddnewDTO 新增的商品属性模板对象
     */
    @Transactional
    void addNew(AttributeTemplateAddNewDTO attributeTemplateAddnewDTO);

    /**
     * 删除商品属性模板
     *
     * @param id 被删除的商品属性模板的id
     */
    @Transactional
    void deleteById(Long id);

    /**
     * 更新商品属性模板
     *
     * @param id                         被修改的商品属性模板的id
     * @param attributeTemplateUpdateDTO 新的相关值的对象
     */
    void updateById(Long id, AttributeTemplateUpdateDTO attributeTemplateUpdateDTO);

    /**
     * 根据属性模板id查询属性模板详情，将包括其下所有属性列表
     *
     * @param id 属性模板id
     * @return 匹配的属性模板详情，如果没有匹配的数据，则抛出异常
     */
    AttributeTemplateDetailsVO getDetailsById(Long id);

    /**
     * 根据属性模板id查询属性模板详情，将包括其下的销售属性列表
     *
     * @param id 属性模板id
     * @return 匹配的属性模板详情，如果没有匹配的数据，则抛出异常
     */
    AttributeTemplateDetailsVO getDetailsIncludeSaleAttributeById(Long id);

    /**
     * 根据属性模板id查询属性模板详情，将包括其下的非销售属性列表
     *
     * @param id 属性模板id
     * @return 匹配的属性模板详情，如果没有匹配的数据，则抛出异常
     */
    AttributeTemplateDetailsVO getDetailsIncludeNonSaleAttributeById(Long id);

    /**
     * 查询商品属性模板列表
     *
     * @param pageNum  页码
     * @param pageSize 每页记录数
     * @return 商品属性模板的列表，如果无记录，则返回长度为0的列表
     */
    JsonPage<AttributeTemplateStandardVO> list(Integer pageNum, Integer pageSize);

    /**
     * 根据类别id查询属性模板列表
     *
     * @param categoryId 类别id
     * @return 属性模板列表，如果没有匹配的数据，将返回长度为0的列表
     */
    List<AttributeTemplateListItemVO> listByCategoryId(Long categoryId);

}
