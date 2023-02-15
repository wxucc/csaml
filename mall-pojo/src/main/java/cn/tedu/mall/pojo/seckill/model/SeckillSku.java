package cn.tedu.mall.pojo.seckill.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
/**
 * <p>
 *
 * </p>
 *
 * @author tedu.cn
 * @since 2022-02-23
 */
@Data
public class SeckillSku implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 表主键
     */
    private Long id;

    /**
     * 秒杀商品sku关联id
     */
    private Long skuId;

    /**
     * 秒杀商品spu关联id
     */
    private Long spuId;

    /**
     * 秒杀商品库存数
     */
    private Integer seckillStock;

    /**
     * 秒杀价钱
     */
    private BigDecimal seckillPrice;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 更新时间
     */
    private LocalDateTime gmtModified;

    /**
     * 秒杀商品数量限制,最多一个用户可以秒杀的商品个数,如果是0表示没有限制
     */
    private Integer seckillLimit;
}
