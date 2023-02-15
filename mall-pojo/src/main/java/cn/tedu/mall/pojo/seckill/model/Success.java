package cn.tedu.mall.pojo.seckill.model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 秒杀成功表格
 * </p>
 *
 * @author tedu.cn
 * @since 2022-02-23
 */
@Data
public class Success implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 关联秒杀id
     */
    private Long seckillId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 手机号码(冗余)
     */
    private String userPhone;

    /**
     * SKU id
     */
    private Long skuId;

    /**
     * 商品SKU标题（冗余）
     */
    private String title;

    /**
     * 商品SKU图片URL（第1张）
     */
    private String mainPicture;

    /**
     * 秒杀商品单价(加入时)
     */
    private BigDecimal seckillPrice;

    /**
     * 秒杀商品数量
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

    private String barCode;

    private String data;

    private String orderSn;
}
