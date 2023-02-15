package cn.tedu.mall.pojo.ums.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 用户收货地址表
 * </p>
 *
 * @author tedu.cn
 * @since 2022-02-22
 */
@Data
public class DeliveryAddressEditDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(
            value="地址iD",
            name="id",
            example = "1")
    private Long id;
    /**
     * 联系人姓名
     */
    @ApiModelProperty(
            value="联系人姓名",
            name="contactName",
            example = "王先生")
    private String contactName;

    /**
     * 联系电话
     */
    @ApiModelProperty(
            value="联系电话",
            name="mobilePhone",
            example = "18899997788")
    private String mobilePhone;

    /**
     * 固定电话
     */
    @ApiModelProperty(
            value="固定电话",
            name="telephone",
            example = "010-66775566")
    private String telephone;

    /**
     * 省-代号
     */
    @ApiModelProperty(
            value="省-代号",
            name="provinceCode",
            example = "110000")
    private String provinceCode;

    /**
     * 省-名称
     */
    @ApiModelProperty(
            value="省-名称",
            name="provinceName",
            example = "北京")
    private String provinceName;

    /**
     * 市-代号
     */
    @ApiModelProperty(
            value="市-代号",
            name="cityCode",
            example = "110000")
    private String cityCode;

    /**
     * 市-名称
     */
    @ApiModelProperty(
            value="市-名称",
            name="cityName",
            example = "北京")
    private String cityName;

    /**
     * 区-代号
     */
    @ApiModelProperty(
            value="区-代号",
            name="districtCode",
            example = "110103")
    private String districtCode;

    /**
     * 区-名称
     */
    @ApiModelProperty(
            value="区-名称",
            name="districtName",
            example = "海淀")
    private String districtName;

    /**
     * 街道-代号
     */
    @ApiModelProperty(
            value="街道-代号",
            name="streetCode",
            example = "00005")
    private String streetCode;

    /**
     * 街道-名称
     */
    @ApiModelProperty(
            value="街道-名称",
            name="streetName",
            example = "中关村街道")
    private String streetName;

    /**
     * 详细地址
     */
    @ApiModelProperty(
            value="详细地址",
            name="detailedAddress",
            example = "中关村软件园28-3-405")
    private String detailedAddress;

    /**
     * 标签，例如：家、公司、学校
     */
    @ApiModelProperty(
            value="标签，例如：家、公司、学校",
            name="tag",
            example = "公司")
    private String tag;

    /**
     * 是否为默认地址，1=默认，0=非默认
     */
    @ApiModelProperty(
            value="是否为默认地址，1=默认，0=非默认",
            name="defaultAddress")
    private Integer defaultAddress;
}
