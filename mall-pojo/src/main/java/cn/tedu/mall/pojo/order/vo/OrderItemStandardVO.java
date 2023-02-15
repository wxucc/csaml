package cn.tedu.mall.pojo.order.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ApiModel(value="订单商品SIMPLEVO")
public class OrderItemStandardVO implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="订单商品id")
    private Long id;
    @ApiModelProperty(value="订单id")
    private Long orderId;
    /**
     * 数据创建时间
     */
    @ApiModelProperty(value="创建时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime gmtCreate;

    /**
     * 数据最后修改时间
     */
    @ApiModelProperty(value="更新时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime gmtModified;

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
     * 商品SKU全部属性，使用JSON格式表示（冗余）
     */
    @ApiModelProperty(value="商品SKU全部属性",notes="（冗余，历史）",example = "{\"attributes\":[{\"id\":1,\"name\":\"屏幕尺寸\",\"value\":\"6.1寸\"},{\"id\":3,\"name\":\"颜色\",\"value\":\"黑色\"},{\"id\":5,\"name\":\"运行内存\",\"value\":\"16GB\"}]}")
    private String data;
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
