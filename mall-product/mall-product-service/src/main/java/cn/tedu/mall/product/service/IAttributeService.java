package cn.tedu.mall.product.service;

import cn.tedu.mall.common.restful.JsonPage;
import cn.tedu.mall.pojo.product.dto.AttributeAddNewDTO;
import cn.tedu.mall.pojo.product.dto.AttributeUpdateDTO;
import cn.tedu.mall.pojo.product.vo.AttributeDetailsVO;
import cn.tedu.mall.pojo.product.vo.AttributeStandardVO;

/**
 * <p>属性业务接口</p>
 *
 * @author java.tedu.cn
 * @since 2021-11-30
 */
public interface IAttributeService {

    /**
     * 增加商品属性
     *
     * @param attributeDTO 新增的商品属性对象
     */
    void addNew(AttributeAddNewDTO attributeDTO);

    /**
     * 删除商品属性
     *
     * @param id 被删除的商品属性的id
     */
    void deleteById(Long id);

    /**
     * 更新商品属性
     *
     * @param id           被修改的商品属性的id
     * @param attributeDTO 新的相关值的对象
     */
    void updateById(Long id, AttributeUpdateDTO attributeDTO);

    /**
     * 根据属性id查询属性详细信息（含所属模板详情）
     *
     * @param id 属性id
     * @return 匹配的属性详细信息，如果没有匹配的数据，则抛出异常
     */
    AttributeDetailsVO getDetailsById(Long id);

    /**
     * 查询商品属性列表
     *
     * @param templateId 商品属性模板id
     * @param pageNum    页码
     * @param pageSize   每页记录数
     * @return 商品属性的列表，如果无记录，则返回长度为0的列表
     */
    JsonPage<AttributeStandardVO> listByTemplateId(Long templateId, Integer pageNum, Integer pageSize);

    /**
     * 查询商品属性列表
     *
     * @param templateId 商品属性模板id
     * @param pageNum    页码
     * @param pageSize   每页记录数
     * @return 商品属性的列表，如果无记录，则返回长度为0的列表
     */
    JsonPage<AttributeStandardVO> listSaleAttributesByTemplateId(Long templateId, Integer pageNum, Integer pageSize);

    /**
     * 查询商品属性列表
     *
     * @param templateId 商品属性模板id
     * @param pageNum    页码
     * @param pageSize   每页记录数
     * @return 商品属性的列表，如果无记录，则返回长度为0的列表
     */
    JsonPage<AttributeStandardVO> listNonSaleAttributesByTemplateId(Long templateId, Integer pageNum, Integer pageSize);

}
