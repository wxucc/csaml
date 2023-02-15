package cn.tedu.mall.pojo.product.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class AttributeTemplateListItemVO implements Serializable {

    /**
     * 商品属性id
     */
    @ApiModelProperty(value = "商品属性id", position = 1)
    private Long id;

    /**
     * 属性模板名称
     */
    @ApiModelProperty(value = "属性模板名称", position = 2)
    private String name;

}
