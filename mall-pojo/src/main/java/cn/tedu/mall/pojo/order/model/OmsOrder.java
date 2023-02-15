package cn.tedu.mall.pojo.order.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 订单数据表
 * </p>
 *
 * @author tedu.cn
 * @since 2022-02-16
 */
@Data
public class OmsOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 订单编号
     */

    private String sn;

    /**
     * 用户id
     */

    private Long userId;

    /**
     * 联系人姓名（冗余，历史）
     */
    private String contactName;

    /**
     * 联系电话（冗余，历史）
     */
    private String mobilePhone;

    /**
     * 固定电话（冗余，历史）
     */
    private String telephone;

    /**
     * 省-代号（冗余，历史）
     */
    private String provinceCode;

    /**
     * 省-名称（冗余，历史）
     */
    private String provinceName;

    /**
     * 市-代号（冗余，历史）
     */
    private String cityCode;

    /**
     * 市-名称（冗余，历史）
     */
    private String cityName;

    /**
     * 区-代号（冗余，历史）
     */
    private String districtCode;

    /**
     * 区-名称（冗余，历史）
     */
    private String districtName;

    /**
     * 街道-代号（冗余，历史）
     */
    private String streetCode;

    /**
     * 街道-名称（冗余，历史）
     */
    private String streetName;

    /**
     * 详细地址（冗余，历史）
     */
    private String detailedAddress;

    /**
     * 标签（冗余，历史），例如：家、公司、学校
     */
    private String tag;

    /**
     * 支付方式，0=银联，1=微信，2=支付宝
     */
    private Integer paymentType;

    /**
     * 状态，0=未支付，1=已关闭（超时未支付），2=已取消，3=已支付，4=已签收，5=已拒收，6=退款处理中，7=已退款
     */
    private Integer state;

    /**
     * 积分
     */
    private Integer rewardPoint;

    /**
     * 商品原总价
     */
    private BigDecimal amountOfOriginalPrice;

    /**
     * 运费总价
     */
    private BigDecimal amountOfFreight;

    /**
     * 优惠金额
     */
    private BigDecimal amountOfDiscount;


    /**
     * 实际支付
     */
    private BigDecimal amountOfActualPay;

    /**
     * 下单时间
     */
    private LocalDateTime gmtOrder;

    /**
     * 支付时间
     */

    private LocalDateTime gmtPay;

    /**
     * 数据创建时间
     */

    private LocalDateTime gmtCreate;

    /**
     * 数据最后修改时间
     */

    private LocalDateTime gmtModified;


}
