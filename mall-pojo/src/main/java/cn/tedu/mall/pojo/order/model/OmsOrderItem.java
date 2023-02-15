package cn.tedu.mall.pojo.order.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 订单商品数据表
 * </p>
 *
 * @author tedu.cn
 * @since 2022-02-16
 */
@Data
public class OmsOrderItem implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 订单id
     */

    private Long orderId;

    /**
     * SKU id
     */

    private Long skuId;

    /**
     * 商品SKU标题（冗余，历史）
     */

    private String title;

    /**
     * 商品SKU商品条型码（冗余）
     */

    private String barCode;

    /**
     * 商品SKU全部属性，使用JSON格式表示（冗余）
     */
    private String data;

    /**
     * 商品SKU图片URL（第1张）（冗余）
     */

    private String mainPicture;

    /**
     * 商品SKU单价（冗余，历史）
     */

    private BigDecimal price;

    /**
     * 商品SKU购买数量
     */

    private Integer quantity;

    /**
     * 数据创建时间
     */

    private LocalDateTime gmtCreate;

    /**
     * 数据最后修改时间
     */
    private LocalDateTime gmtModified;


}
