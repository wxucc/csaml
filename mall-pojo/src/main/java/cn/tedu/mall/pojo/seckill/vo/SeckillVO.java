package cn.tedu.mall.pojo.seckill.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Deprecated
@ApiModel(value="单个秒杀商品")
@Data
public class SeckillVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 表主键
     */
    @ApiModelProperty(value="秒杀id")
    private Long id;

    /**
     * 秒杀商品sku关联id
     */
    @ApiModelProperty(value="秒杀商品sku关联id")
    private Long skuId;

    /**
     * 秒杀商品spu关联id
     */
    @ApiModelProperty(value="秒杀商品spu关联id")
    private Long spuId;

    /**
     * 秒杀商品库存数
     */
    @ApiModelProperty(value="秒杀商品库存数")
    private Integer seckillStock;

    /**
     * 秒杀开始时间
     */
    @ApiModelProperty(value="秒杀开始时间")
    private LocalDateTime startTime;

    /**
     * 秒杀结束时间
     */
    @ApiModelProperty(value="秒杀结束时间")
    private LocalDateTime endTime;

    /**
     * 秒杀价钱
     */
    @ApiModelProperty(value="秒杀价钱")
    private BigDecimal seckillPrice;

    /**
     * 商品原价
     */
    @ApiModelProperty(value="商品原价")
    private BigDecimal originPrice;

    /**
     * 创建时间
     */
    @ApiModelProperty(value="秒杀商品sku关联id")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime gmtCreate;

    /**
     * 更新时间
     */
    @ApiModelProperty(value="更新时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime gmtModified;

    /**
     * 秒杀商品数量限制,最多一个用户可以秒杀的商品个数,如果是0表示没有限制
     */
    @ApiModelProperty(value="秒杀商品数量限制")
    private Integer seckillLimit;
}
