package cn.tedu.mall.pojo.order.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 购物车数据表
 * </p>
 *
 * @author tedu.cn
 * @since 2022-02-16
 */
@Data
public class OmsCart implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;

    /**
     * 用户id
     */

    private Long userId;

    /**
     * SKU id
     */

    private Long skuId;

    /**
     * 商品SKU标题（冗余）
     */

    private String title;

    /**
     * 商品SKU图片URL（第1张）（冗余）
     */

    private String mainPicture;

    /**
     * 商品SKU单价（加入时）
     */

    private BigDecimal price;

    /**
     * 商品SKU购买数量
     */

    private Integer quantity;
    /**
     * sku条形码
     */

    /**
     * 数据创建时间
     */

    private LocalDateTime gmtCreate;

    /**
     * 数据最后修改时间
     */

    private LocalDateTime gmtModified;

    private String data;
    private String barCode;
}
