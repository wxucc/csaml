package cn.tedu.mall.pojo.product.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class SkuSpecificationStandardVO implements Serializable {

    /**
     * 数据id
     */
    @ApiModelProperty(value = "数据记录id", position = 1)
    private Integer id;

    /**
     * SKU id
     */
    @ApiModelProperty(value = "SKU id", position = 2)
    private Long skuId;

    /**
     * 属性id
     */
    @ApiModelProperty(value = "属性id", position = 3)
    private Long attributeId;

    /**
     * 属性名称
     */
    @ApiModelProperty(value = "属性名称", position = 4)
    private String attributeName;

    /**
     * 属性值
     */
    @ApiModelProperty(value = "属性值", position = 5)
    private String attributeValue;

    /**
     * 自动补充的计量单位
     */
    @ApiModelProperty(value = "自动补充的计量单位", position = 6)
    private String unit;

    /**
     * 自定义排序序号
     */
    @ApiModelProperty(value = "自定义排序序号", position = 7)
    private Integer sort;

}
