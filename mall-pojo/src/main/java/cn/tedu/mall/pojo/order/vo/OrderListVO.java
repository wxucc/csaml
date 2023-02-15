package cn.tedu.mall.pojo.order.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 查询我的订单展示订单数据
 * 包括 订单id 编号 用户id,收货人姓名 状态,金额,下单时间
 * </p>
 *
 * @author tedu.cn
 * @since 2022-02-16
 */
@Data
@ApiModel(value="")
public class OrderListVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="订单id")
    private Long id;

    /**
     * 订单编号
     */
    @ApiModelProperty(value="订单编号")
    private String sn;

    /**
     * 用户id
     */
    @ApiModelProperty(value="用户id")
    private Long userId;

    /**
     * 联系人姓名（冗余，历史）
     */
    @ApiModelProperty(value="联系人姓名")
    private String contactName;

    /**
     * 状态，0=未支付，1=已关闭（超时未支付），2=已取消，3=已支付，4=已签收，5=已拒收，6=退款处理中，7=已退款
     */
    @ApiModelProperty(value="状态，0=未支付，1=已关闭（超时未支付），2=已取消，3=已支付，4=已签收，5=已拒收，6=退款处理中，7=已退款",example = "0")
    private Integer state;

    /**
     * 实际支付
     */
    @ApiModelProperty(value="实际支付")
    private BigDecimal amountOfActualPay;



    /**
     * 下单时间
     */
    @ApiModelProperty(value="下单时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime gmtOrder;
    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建订单时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime gmtCreate;
    /**
     * 支付时间
     */
    @ApiModelProperty(value="支付时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime gmtPay;
    /**
     * 修改时间
     */
    @ApiModelProperty(value="修改时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime gmtModified;

    /**
     * 订单商品列表
     */
    private List<OrderItemListVO> orderItems;
}
