package cn.tedu.mall.pojo.order.dto;

import cn.tedu.mall.pojo.valid.order.CartRegExpression;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel(value = "购物车新增DTO")
@Data
public class CartAddDTO implements CartRegExpression,Serializable {
    private static final long serialVersionUID = 1L;
    private static final String VALIDATE_MESSAGE_PREFIX = "新增购物车失败，";
    /**
     * SKU id
     */
    @ApiModelProperty(value="SKU id",example = "1")
    @NotNull(message = VALIDATE_MESSAGE_PREFIX+"请提供SKUID")
    private Long skuId;

    /**
     * 商品SKU标题（冗余）
     */
    @ApiModelProperty(value="品SKU标题",example = "2021年新款，小米11 Ultra黑色512G，16G超大内存120Hz高刷67w快充")
    @NotNull(message = VALIDATE_MESSAGE_PREFIX+"请提供TITLE")
    private String title;

    /**
     * 商品SKU图片URL（第1张）（冗余）
     */
    @ApiModelProperty(value="商品SKU图片URL（第1张）",example = "picture1")
    private String mainPicture;

    /**
     * 商品SKU单价（加入时）
     */
    @ApiModelProperty(value="商品SKU单价",example = "6999.99")
    @NotNull(message = VALIDATE_MESSAGE_PREFIX+"请提供商品单价")
    private BigDecimal price;

    /**
     * 商品SKU购买数量
     */
    @ApiModelProperty(value="商品SKU购买数量",example = "10")
    @NotNull(message = VALIDATE_MESSAGE_PREFIX+"请提供购买商品数量")
    private Integer quantity;
}
