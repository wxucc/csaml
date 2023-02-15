package cn.tedu.mall.pojo.order.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@ApiModel(value="购物车VO")
@Data
public class CartStandardVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="购物车id")
    private Long id;

    /**
     * 用户id
     */
    @ApiModelProperty(value="用户id")
    private Long userId;

    /**
     * SKU id
     */
    @ApiModelProperty(value="商品SKU ID")
    private Long skuId;

    /**
     * 商品SKU标题（冗余）
     */
    @ApiModelProperty(value="商品SKU标题")
    private String title;

    /**
     * sku条形码
     */
    @ApiModelProperty(value="商品SKU条形码")
    private String barCode;

    /**
     * 商品SKU全部属性，使用JSON格式表示（冗余）
     */
    @ApiModelProperty(value="商品SKU全部属性JSON格式数据")
    private String data;

    /**
     * 商品SKU图片URL（第1张）（冗余）
     */
    @ApiModelProperty(value="品SKU图片URL（第1张）")
    private String mainPicture;

    /**
     * 商品SKU单价（加入时）
     */
    @ApiModelProperty(value="商品SKU单价")
    private BigDecimal price;

    /**
     * 商品SKU购买数量
     */
    @ApiModelProperty(value="商品SKU购买数量")
    private Integer quantity;

    /**
     * 数据创建时间
     */
    @ApiModelProperty(value="数据创建时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime gmtCreate;

    /**
     * 数据最后修改时间
     */
    @ApiModelProperty(value="数据最后修改时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime gmtModified;
}
