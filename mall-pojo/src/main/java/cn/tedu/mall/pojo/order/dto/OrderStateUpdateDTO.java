package cn.tedu.mall.pojo.order.dto;

import cn.tedu.mall.pojo.valid.order.OrderRegExpression;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@ApiModel(value="更新订单状态DTO")
@Data
public class OrderStateUpdateDTO implements OrderRegExpression, Serializable {
    private static final String VALIDATE_MESSAGE_PREFIX = "订单状态修改失败，";
    @ApiModelProperty(value="订单id",example = "2000")
    @NotNull(message = VALIDATE_MESSAGE_PREFIX+"请提供订单id")
    private Long id;
    @ApiModelProperty(value="订单状态",notes="前台用户可以，取消订单",example = "4")
    @NotNull(message = VALIDATE_MESSAGE_PREFIX+"请提供最新订单状态值")
    private Integer state;
}
