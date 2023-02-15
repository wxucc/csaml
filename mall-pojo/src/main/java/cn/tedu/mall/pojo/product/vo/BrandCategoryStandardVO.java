package cn.tedu.mall.pojo.product.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class BrandCategoryStandardVO implements Serializable {

    /**
     * 绑定记录id
     */
    @ApiModelProperty(value = "数据记录id", position = 1)
    private Long recordId;

    /**
     * 品牌id
     */
    @ApiModelProperty(value = "品牌id", position = 2)
    private Long brandId;

    /**
     * 品牌名称
     */
    @ApiModelProperty(value = "品牌名称", position = 3)
    private String brandName;

    /**
     * 品牌名称的拼音
     */
    @ApiModelProperty(value = "品牌名称的拼音", position = 4)
    private String brandPinyin;

    /**
     * 品牌logo的URL
     */
    @ApiModelProperty(value = "品牌logo的URL", position = 5)
    private String brandLogo;

    /**
     * 品牌简介
     */
    @ApiModelProperty(value = "品牌简介", position = 6)
    private String brandDescription;

    /**
     * 是否启用，1=启用，0=未启用
     */
    @ApiModelProperty(value = "品牌是否启用，1=启用，0=未启用", position = 7)
    private Integer brandEnable;

    /**
     * 类别id
     */
    @ApiModelProperty(value = "类别id", position = 8)
    private Long categoryId;

    /**
     * 类别名称
     */
    @ApiModelProperty(value = "类别名称", position = 9)
    private String categoryName;

    /**
     * 父级类别id，如果无父级，则为0
     */
    @ApiModelProperty(value = "父级类别id，如果无父级，则为0", position = 10)
    private Long categoryParentId;

    /**
     * 图标图片的URL
     */
    @ApiModelProperty(value = "类别图标图片的URL", position = 11)
    private String categoryIcon;

    /**
     * 是否启用，1=启用，0=未启用
     */
    @ApiModelProperty(value = "类别是否启用，1=启用，0=未启用", position = 12)
    private Integer categoryEnable;

    /**
     * 是否显示在导航栏中，1=启用，0=未启用
     */
    @ApiModelProperty(value = "类别是否显示在导航栏中，1=启用，0=未启用", position = 13)
    private Integer categoryDisplay;

}
