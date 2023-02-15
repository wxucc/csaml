package cn.tedu.mall.pojo.ums.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户收货地址表
 * </p>
 *
 * @author tedu.cn
 * @since 2022-02-22
 */
@Data
public class DeliveryAddressStandardVO implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="地址id",name="id")
    private Long id;

    /**
     * 用户id
     */
    @ApiModelProperty(value="用户id",name="userId")
    private Long userId;

    /**
     * 联系人姓名
     */
    @ApiModelProperty(value="联系人姓名",name="contactName")
    private String contactName;

    /**
     * 联系电话
     */
    @ApiModelProperty(value="联系电话",name="mobilePhone")
    private String mobilePhone;

    /**
     * 固定电话
     */
    @ApiModelProperty(value="固定电话",name="telephone")
    private String telephone;

    /**
     * 省-代号
     */
    @ApiModelProperty(value="省-代号",name="provinceCode")
    private String provinceCode;

    /**
     * 省-名称
     */
    @ApiModelProperty(value="省-名称",name="provinceName")
    private String provinceName;

    /**
     * 市-代号
     */
    @ApiModelProperty(value="市-代号",name="cityCode")
    private String cityCode;

    /**
     * 市-名称
     */
    @ApiModelProperty(value="市-名称",name="cityName")
    private String cityName;

    /**
     * 区-代号
     */
    @ApiModelProperty(value="区-代号",name="districtCode")
    private String districtCode;

    /**
     * 区-名称
     */
    @ApiModelProperty(value="区-名称",name="districtName")
    private String districtName;

    /**
     * 街道-代号
     */
    @ApiModelProperty(value="街道-代号",name="streetCode")
    private String streetCode;

    /**
     * 街道-名称
     */
    @ApiModelProperty(value="街道-名称",name="streetName")
    private String streetName;

    /**
     * 详细地址
     */
    @ApiModelProperty(value="详细地址",name="detailedAddress")
    private String detailedAddress;

    /**
     * 标签，例如：家、公司、学校
     */
    @ApiModelProperty(value="标签，例如：家、公司、学校",name="tag")
    private String tag;

    /**
     * 是否为默认地址，1=默认，0=非默认
     */
    @ApiModelProperty(value="是否为默认地址，1=默认，0=非默认",name="defaultAddress")
    private Integer defaultAddress;

    /**
     * 数据创建时间
     */
    @ApiModelProperty(value="数据创建时间",name="gmtCreate")
    private LocalDateTime gmtCreate;

    /**
     * 数据最后修改时间
     */
    @ApiModelProperty(value="数据最后修改时间",name="gmtModified")
    private LocalDateTime gmtModified;


}
