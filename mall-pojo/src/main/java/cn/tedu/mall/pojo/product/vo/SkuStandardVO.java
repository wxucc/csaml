package cn.tedu.mall.pojo.product.vo;

import com.fasterxml.jackson.annotation.JsonRawValue;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class SkuStandardVO implements Serializable {

    /**
     * id
     */
    @ApiModelProperty(value = "SKU id", position = 1)
    private Long id;

    /**
     * SPU id
     */
    @ApiModelProperty(value = "SPU id", position = 2)
    private Long spuId;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题", position = 3)
    private String title;

    /**
     * 条型码
     */
    @ApiModelProperty(value = "条型码", position = 4)
    private String barCode;

    /**
     * 属性模板id
     */
    @ApiModelProperty(value = "属性模板id", position = 5)
    private Long attributeTemplateId;

    /**
     * 全部属性，使用JSON格式表示（冗余）
     */
    @ApiModelProperty(value = "全部属性", position = 6)
    @JsonRawValue
    private String specifications;

    /**
     * 相册id
     */
    @ApiModelProperty(value = "相册id", position = 7)
    private Long albumId;

    /**
     * 组图URLs，使用JSON格式表示
     */
    @ApiModelProperty(value = "组图URL", position = 8)
    //@JsonRawValue
    private String pictures;

    /**
     * 单价
     */
    @ApiModelProperty(value = "单价", position = 9)
    private BigDecimal price;

    /**
     * 当前库存
     */
    @ApiModelProperty(value = "当前库存", position = 10)
    private Integer stock;

    /**
     * 库存预警阈值
     */
    @ApiModelProperty(value = "库存预警阈值", position = 11)
    private Integer stockThreshold;

    /**
     * 销量（冗余）
     */
    @ApiModelProperty(value = "销量", position = 12)
    private Integer sales;

    /**
     * 买家评论数量总和（冗余）
     */
    @ApiModelProperty(value = "买家评论数量", position = 13)
    private Integer commentCount;

    /**
     * 买家好评数量总和（冗余）
     */
    @ApiModelProperty(value = "买家好评数量", position = 14)
    private Integer positiveCommentCount;

    /**
     * 自定义排序序号
     */
    @ApiModelProperty(value = "自定义排序序号", position = 15)
    private Integer sort;

}
