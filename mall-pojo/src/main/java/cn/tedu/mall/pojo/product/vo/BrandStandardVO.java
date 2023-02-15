package cn.tedu.mall.pojo.product.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class BrandStandardVO implements Serializable {

    /**
     * 品牌id
     */
    @ApiModelProperty(value = "品牌id", position = 1)
    private Long id;

    /**
     * 品牌名称
     */
    @ApiModelProperty(value = "品牌名称", position = 2)
    private String name;

    /**
     * 品牌名称的拼音
     */
    @ApiModelProperty(value = "品牌名称的拼音", position = 3)
    private String pinyin;

    /**
     * 品牌logo的URL
     */
    @ApiModelProperty(value = "品牌logo的URL", position = 4)
    private String logo;

    /**
     * 品牌简介
     */
    @ApiModelProperty(value = "品牌简介", position = 5)
    private String description;

    /**
     * 关键词列表，各关键词使用英文的逗号分隔
     */
    @ApiModelProperty(value = "关键词列表，各关键词使用英文的逗号分隔", position = 6)
    private String keywords;

    /**
     * 销量（冗余）
     */
    @ApiModelProperty(value = "销量", position = 7)
    private Integer sales;

    /**
     * 商品种类数量总和（冗余）
     */
    @ApiModelProperty(value = "商品种类数量", position = 8)
    private Integer productCount;

    /**
     * 买家评论数量总和（冗余）
     */
    @ApiModelProperty(value = "买家评论数量", position = 9)
    private Integer commentCount;

    /**
     * 买家好评数量总和（冗余）
     */
    @ApiModelProperty(value = "买家好评数量", position = 10)
    private Integer positiveCommentCount;

    /**
     * 是否启用，1=启用，0=未启用
     */
    @ApiModelProperty(value = "是否启用，1=启用，0=未启用", position = 11)
    private Integer enable;

    /**
     * 自定义排序序号
     */
    @ApiModelProperty(value = "自定义排序序号", position = 12)
    private Integer sort;
}
