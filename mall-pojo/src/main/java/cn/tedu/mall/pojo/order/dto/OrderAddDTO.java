package cn.tedu.mall.pojo.order.dto;

import cn.tedu.mall.pojo.valid.order.OrderRegExpression;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@ApiModel(value="订单新增DTO")
@Data
public class OrderAddDTO implements OrderRegExpression,Serializable {
    private static final String VALIDATE_MESSAGE_PREFIX = "新增订单失败，";
    private static final long serialVersionUID = 1L;
    /**
     * 联系人姓名（冗余，历史）
     */
    @ApiModelProperty(value="联系人姓名",notes="冗余，历史",example = "刘先生",required = true)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX+"请填写联系人")
    @Pattern(regexp = REGEXP_CONTACT_NAME,message = MESSAGE_CONTACT_NAME)
    private String contactName;

    /**
     * 联系电话（冗余，历史）
     */
    @ApiModelProperty(value="联系电话",notes="冗余，历史",example = "13800138001",required = true)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX+"请填写电话号码")
    @Pattern(regexp = REGEXP_MOBILE_PHONE,message = MESSAGE_MOBILE_PHONE)
    private String mobilePhone;

    /**
     * 固定电话（冗余，历史）
     */
    @ApiModelProperty(value="固定电话",notes="冗余，历史",example = "010-88888888",required = true)
    //@Pattern(regexp = REGEXP_TELEPHONE,message = MESSAGE_TELEPHONE)
    private String telephone;

    /**
     * 省-代号（冗余，历史）
     */
    @ApiModelProperty(value="省-代号",notes="冗余，历史",example = "110000",required = true)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX+"请填写所属省份代号")
    private String provinceCode;

    /**
     * 省-名称（冗余，历史）
     */
    @ApiModelProperty(value="省-名称",notes="冗余，历史",example = "北京市",required = true)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX+"请填写所属省份名称")
    private String provinceName;

    /**
     * 市-代号（冗余，历史）
     */
    @ApiModelProperty(value="市-代号",notes="冗余，历史",example = "110100",required = true)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX+"请填写所属市代号")
    private String cityCode;

    /**
     * 市-名称（冗余，历史）
     */
    @ApiModelProperty(value="市-名称",notes="冗余，历史",example = "市辖区",required = true)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX+"请填写所市名称")
    private String cityName;

    /**
     * 区-代号（冗余，历史）
     */
    @ApiModelProperty(value="区-代号",notes="冗余，历史",example = "110103",required = true)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX+"请填写所区代号")
    private String districtCode;

    /**
     * 区-名称（冗余，历史）
     */
    @ApiModelProperty(value="区-名称",notes="冗余，历史",example = "海淀区",required = true)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX+"请填写所区名称")
    private String districtName;

    /**
     * 街道-代号（冗余，历史）
     */
    @ApiModelProperty(value="街道-代号",notes="冗余，历史",example = "00005",required = true)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX+"请填写街道代号")
    private String streetCode;

    /**
     * 街道-名称（冗余，历史）
     */
    @ApiModelProperty(value="街道-名称",notes="冗余，历史",example = "中关村街道",required = true)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX+"请填写街道名称")
    private String streetName;

    /**
     * 详细地址（冗余，历史）
     */
    @ApiModelProperty(value="详细地址",notes="冗余，历史",example = "中关村软件园28-3-405",required = true)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX+"请填写详细地址")
    private String detailedAddress;

    /**
     * 标签（冗余，历史），例如：家、公司、学校
     */
    @ApiModelProperty(value="标签",notes="冗余，历史",example = "公司",required = false)
    private String tag;

    /**
     * 支付方式，0=银联，1=微信，2=支付宝
     */
    @ApiModelProperty(value="支付方式,0=银联，1=微信，2=支付宝",example = "1",required = true)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX+"请填写支付方式,0=银联，1=微信，2=支付宝")
    private Integer paymentType;

    /**
     * 状态，0=未支付，1=已关闭（超时未支付），2=已取消，3=已支付，4=已签收，5=已拒收，6=退款处理中，7=已退款
     */
    @ApiModelProperty(value="状态，0=未支付，1=已关闭（超时未支付），2=已取消，3=已支付，4=已签收，5=已拒收，6=退款处理中，7=已退款",example = "0")
    private Integer state;

    /**
     * 积分
     */
    @ApiModelProperty(value="积分",example = "100")
    private Integer rewardPoint;

    /**
     * 商品原总价
     */
    @ApiModelProperty(value="商品原总价",example = "5000")
    @NotNull(message = VALIDATE_MESSAGE_PREFIX+"请提供商品总原价")
    private BigDecimal amountOfOriginalPrice;

    /**
     * 运费总价
     */
    @ApiModelProperty(value="运费总价",example = "100")
    @NotNull(message = VALIDATE_MESSAGE_PREFIX+"请提供运费价")
    private BigDecimal amountOfFreight;

    /**
     * 优惠金额
     */
    @ApiModelProperty(value="优惠金额",example = "0")
    @NotNull(message = VALIDATE_MESSAGE_PREFIX+"请提供优惠金额")
    private BigDecimal amountOfDiscount;

    /**
     * 实际支付
     */
    @ApiModelProperty(value="实际支付",example = "0")
    @NotNull(message = VALIDATE_MESSAGE_PREFIX+"请提供最终支付金额")
    private BigDecimal amountOfActualPay;
    /**
     * 用户id
     */
    @ApiModelProperty(value="用户Id",example = "1")
    private Long userId;

    /**
     * 订单商品列表
     */
    private List<OrderItemAddDTO> orderItems;

}
