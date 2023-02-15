package cn.tedu.mall.pojo.product.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>类别与属性模板关联</p>
 *
 * @author tedu.cn
 * @since 2021-11-30
 */
@Data
public class CategoryAttributeTemplate {

    /**
     * 记录id
     */
    private Long id;

    /**
     * 类别id
     */
    private Long categoryId;

    /**
     * 属性模板id
     */
    private Long attributeTemplateId;

    /**
     * 数据创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 数据最后修改时间
     */
    private LocalDateTime gmtModified;

}
