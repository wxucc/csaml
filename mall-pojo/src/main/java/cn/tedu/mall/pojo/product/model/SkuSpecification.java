package cn.tedu.mall.pojo.product.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>SKU数据</p>
 *
 * @author tedu.cn
 * @since 2021-11-30
 */
@Data
public class SkuSpecification implements Serializable {

    /**
     * 记录id
     */
    private Integer id;

    /**
     * SKU id
     */
    private Long skuId;

    /**
     * 属性id
     */
    private Long attributeId;

    /**
     * 属性名称
     */
    private String attributeName;

    /**
     * 属性值
     */
    private String attributeValue;

    /**
     * 自动补充的计量单位
     */
    private String unit;

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