package cn.tedu.mall.pojo.order.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ApiModel(value="查询我的订单展示订单的商品VO")
public class OrderItemListVO implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="订单商品id")
    private Long id;
    @ApiModelProperty(value="订单id")
    private Long orderId;
    /**
     * SKU id
     */
    @ApiModelProperty(value="skuId",example = "1")
    private Long skuId;
    /**
     * 商品SKU标题（冗余，历史）
     */
    @ApiModelProperty(value="商品SKU标题",notes="（冗余，历史）",example = "2021年新款，小米11 Ultra黑色512G，16G超大内存120Hz高刷67w快充")
    private String title;
    /**
     * 商品SKU商品条型码（冗余）
     */
    @ApiModelProperty(value="商品SKU商品条型码",notes="（冗余，历史）",example = "0")
    private String barCode;

    /**
     * 商品SKU图片URL（第1张）（冗余）
     */
    @ApiModelProperty(value="商品SKU图片URL",notes="（冗余，历史）",example = "picture1")
    private String mainPicture;

    /**
     * 商品SKU单价（冗余，历史）
     */
    @ApiModelProperty(value="商品SKU单价",notes="（冗余，历史）",example = "6999.99")
    private BigDecimal price;

    /**
     * 商品SKU购买数量
     */
    @ApiModelProperty(value="商品SKU购买数量",example = "10")
    private Integer quantity;
}
