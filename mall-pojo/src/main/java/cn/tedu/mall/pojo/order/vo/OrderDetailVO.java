package cn.tedu.mall.pojo.order.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@ApiModel(value="订单详情VO")
@Data
public class OrderDetailVO implements Serializable {
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
     * 联系电话（冗余，历史）
     */
    @ApiModelProperty(value="联系电话")
    private String mobilePhone;

    /**
     * 固定电话（冗余，历史）
     */
    @ApiModelProperty(value="固定电话")
    private String telephone;

    /**
     * 省-代号（冗余，历史）
     */
    @ApiModelProperty(value="省-代号")
    private String provinceCode;

    /**
     * 省-名称（冗余，历史）
     */
    @ApiModelProperty(value="省-名称")
    private String provinceName;

    /**
     * 市-代号（冗余，历史）
     */
    @ApiModelProperty(value="市-代号")
    private String cityCode;

    /**
     * 市-名称（冗余，历史）
     */
    @ApiModelProperty(value="市-名称")
    private String cityName;

    /**
     * 区-代号（冗余，历史）
     */
    @ApiModelProperty(value="区-代号")
    private String districtCode;

    /**
     * 区-名称（冗余，历史）
     */
    @ApiModelProperty(value="区-名称")
    private String districtName;

    /**
     * 街道-代号（冗余，历史）
     */
    @ApiModelProperty(value="街道-代号")
    private String streetCode;

    /**
     * 街道-名称（冗余，历史）
     */
    @ApiModelProperty(value="街道-名称")
    private String streetName;

    /**
     * 详细地址（冗余，历史）
     */
    @ApiModelProperty(value="详细地址")
    private String detailedAddress;

    /**
     * 标签（冗余，历史），例如：家、公司、学校
     */
    @ApiModelProperty(value="标签")
    private String tag;

    /**
     * 支付方式，0=银联，1=微信，2=支付宝
     */
    @ApiModelProperty(value="支付方式,0=银联，1=微信，2=支付宝")
    private Integer paymentType;

    /**
     * 状态，0=未支付，1=已关闭（超时未支付），2=已取消，3=已支付，4=已签收，5=已拒收，6=退款处理中，7=已退款
     */
    @ApiModelProperty(value="状态，0=未支付，1=已关闭（超时未支付），2=已取消，3=已支付，4=已签收，5=已拒收，6=退款处理中，7=已退款",example = "0")
    private Integer state;

    /**
     * 积分
     */
    @ApiModelProperty(value="积分")
    private Integer rewardPoint;

    /**
     * 商品原总价
     */
    @ApiModelProperty(value="商品原总价")
    private BigDecimal amountOfOriginalPrice;

    /**
     * 运费总价
     */
    @ApiModelProperty(value="运费总价")
    private BigDecimal amountOfFreight;

    /**
     * 优惠金额
     */
    @ApiModelProperty(value="优惠金额")
    private BigDecimal amountOfDiscount;

    /**
     * 实际支付
     */
    @ApiModelProperty(value="实际支付")
    private BigDecimal amountOfActualPay;

    /**
     * 订单商品列表
     */
    private List<OrderItemListVO> orderItems;
    /**
     * 下单时间
     */
    @ApiModelProperty(value="下单时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime gmtOrder;

    /**
     * 支付时间
     */
    @ApiModelProperty(value="支付时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime gmtPay;

    /**
     * 数据创建时间
     */
    @ApiModelProperty(value="创建订单时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime gmtCreate;

    /**
     * 数据最后修改时间
     */
    @ApiModelProperty(value="修改订单时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime gmtModified;

}
