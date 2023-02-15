package cn.tedu.mall.pojo.ums.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

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

public class DeliveryAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 用户id
     */

    private Long userId;

    /**
     * 联系人姓名
     */

    private String contactName;

    /**
     * 联系电话
     */

    private String mobilePhone;

    /**
     * 固定电话
     */

    private String telephone;

    /**
     * 省-代号
     */

    private String provinceCode;

    /**
     * 省-名称
     */

    private String provinceName;

    /**
     * 市-代号
     */

    private String cityCode;

    /**
     * 市-名称
     */

    private String cityName;

    /**
     * 区-代号
     */

    private String districtCode;

    /**
     * 区-名称
     */

    private String districtName;

    /**
     * 街道-代号
     */

    private String streetCode;

    /**
     * 街道-名称
     */

    private String streetName;

    /**
     * 详细地址
     */

    private String detailedAddress;

    /**
     * 标签，例如：家、公司、学校
     */

    private String tag;

    /**
     * 是否为默认地址，1=默认，0=非默认
     */

    private Integer defaultAddress;

    /**
     * 数据创建时间
     */

    private LocalDateTime gmtCreate;

    /**
     * 数据最后修改时间
     */

    private LocalDateTime gmtModified;


}
