package cn.tedu.mall.pojo.seckill.vo;

import com.fasterxml.jackson.annotation.JsonRawValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 库存是秒杀库存
 * 价钱有原价和秒杀价
 * 还有一个秒杀数量限制,
 * 其他和Sku相同
 */
@ApiModel(value="秒杀商品SKU的VO",description = "一个包含了秒杀库存,秒杀价,原价,秒杀限制,和sku其他数据的VO对象")
@Data
public class SeckillSkuVO implements Serializable {
    /**
     * id
     */
    @ApiModelProperty(value = "sku的id")
    private Long id;

    /**
     * SPU id
     */
    @ApiModelProperty(value = "SPU id")
    private Long spuId;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String title;

    /**
     * 条型码
     */
    @ApiModelProperty(value = "条型码")
    private String barCode;

    /**
     * 属性模版id
     */
    @ApiModelProperty(value = "属性模版id")
    private Long attributeTemplateId;

    /**
     * 全部属性，使用JSON格式表示（冗余）
     */
    @ApiModelProperty(value = "全部属性，使用JSON格式表示（冗余）")
    @JsonRawValue
    private String specifications;

    /**
     * 相册id
     */
    @ApiModelProperty(value = "相册id")
    private Long albumId;

    /**
     * 组图URLs，使用JSON格式表示
     */
    @ApiModelProperty(value = "组图URLs，使用JSON格式表示")
    private String pictures;

    /**
     * 原价
     */
    @ApiModelProperty(value = "原价")
    private BigDecimal price;
    /**
     * 秒杀价
     */
    @ApiModelProperty(value="秒杀价")
    private BigDecimal seckillPrice;
    /**
     * 秒杀库存
     */
    @ApiModelProperty(value = "秒杀库存")
    private Integer stock;

    /**
     * 库存预警阈值
     */
    @ApiModelProperty(value = "库存预警阈值")
    private Integer stockThreshold;

    /**
     * 销量（冗余）
     */
    @ApiModelProperty(value = "销量（冗余）")
    private Integer sales;

    /**
     * 买家评论数量总和（冗余）
     */
    @ApiModelProperty(value = "买家评论数量总和（冗余）")
    private Integer commentCount;

    /**
     * 买家好评数量总和（冗余）
     */
    @ApiModelProperty(value = "买家好评数量总和（冗余）")
    private Integer positiveCommentCount;

    /**
     * 自定义排序序号
     */
    @ApiModelProperty(value = "自定义排序序号")
    private Integer sort;
    /**
     * 秒杀数量限制
     */
    @ApiModelProperty(value="秒杀商品数量限制")
    private Integer seckillLimit;
}
