package cn.tedu.mall.pojo.order.dto;

import cn.tedu.mall.pojo.valid.order.OrderItemRegExpression;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel(value="订单商品新增DTO")
@Data
public class OrderItemAddDTO implements OrderItemRegExpression,Serializable {

    private static final long serialVersionUID = 1L;
    private static final String VALIDATE_MESSAGE_PREFIX = "新增订单失败，";
    /**
     * SKU id
     */
    @ApiModelProperty(value="skuId",example = "1",required = true)
    @NotNull(message =VALIDATE_MESSAGE_PREFIX+"请提供商品SKUID" )
    private Long skuId;
    /**
     * 商品SKU标题（冗余，历史）
     */
    @ApiModelProperty(value="商品SKU标题",notes="（冗余，历史）",example = "2021年新款，小米11 Ultra黑色512G，16G超大内存120Hz高刷67w快充")
    @NotNull(message =VALIDATE_MESSAGE_PREFIX+"请提供商品标题" )
    private String title;
    /**
     * 商品SKU商品条型码（冗余）
     */
    @ApiModelProperty(value="商品SKU商品条型码",notes="（冗余，历史）",example = "0")
    private String barCode;
    /**
     * 商品SKU全部属性，使用JSON格式表示（冗余）
     */
    @ApiModelProperty(value="商品SKU全部属性",notes="（冗余，历史）",example = "[{\"id\":1,\"name\":\"屏幕尺寸\",\"value\":\"6.1寸\"},{\"id\":3,\"name\":\"颜色\",\"value\":\"黑色\"},{\"id\":5,\"name\":\"运行内存\",\"value\":\"16GB\"}]")
    @NotNull(message =VALIDATE_MESSAGE_PREFIX+"请提供商品全属性json" )
    private String data;
    /**
     * 商品SKU图片URL（第1张）（冗余）
     */
    @ApiModelProperty(value="商品SKU图片URL",notes="（冗余，历史）",example = "picture1")
    @NotNull(message =VALIDATE_MESSAGE_PREFIX+"请提供商品主图片" )
    private String mainPicture;

    /**
     * 商品SKU单价（冗余，历史）
     */
    @ApiModelProperty(value="商品SKU单价",notes="（冗余，历史）",example = "6999.99")
    @NotNull(message =VALIDATE_MESSAGE_PREFIX+"请提供商品单价" )
    private BigDecimal price;

    /**
     * 商品SKU购买数量
     */
    @ApiModelProperty(value="商品SKU购买数量",example = "10")
    @NotNull(message =VALIDATE_MESSAGE_PREFIX+"请提供商品购买数量" )
    private Integer quantity;
}
