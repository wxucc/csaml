package cn.tedu.mall.pojo.product.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>模板的属性与值</p>
 *
 * @author tedu.cn
 * @since 2021-11-30
 */
@Data
public class AttributeTemplateValue implements Serializable {

    /**
     * 记录id
     */
    private Long id;

    /**
     * 模板id
     */
    private Long templateId;

    /**
     * 属性id
     */
    private Long attributeId;

    /**
     * 自定义排序序号
     */
    private Integer sort;

    /**
     * 数据创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 数据最后修改时间
     */
    private LocalDateTime gmtModified;

}