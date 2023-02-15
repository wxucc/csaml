package cn.tedu.mall.pojo.order.dto;

import cn.tedu.mall.pojo.valid.order.CartRegExpression;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@ApiModel(value="购物车更新购买数量DTO")
@Data
public class CartUpdateDTO implements CartRegExpression,Serializable {
    private static final long serialVersionUID = 1L;
    private static final String VALIDATE_MESSAGE_PREFIX = "修改购物车商品数量失败，";
    @ApiModelProperty(value="购物车Id",required = true)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX+"请提供购物车id")
    private Long id;
    @ApiModelProperty(value="购物车sku的购买新数量",required = true)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX+"请提供修改商品数量")
    private Integer quantity;
}
