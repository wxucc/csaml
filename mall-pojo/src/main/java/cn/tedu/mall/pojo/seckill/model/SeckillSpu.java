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
public class SeckillSpu implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long spuId;

    private BigDecimal listPrice;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;


}
