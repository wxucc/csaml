package cn.tedu.mall.pojo.ums.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户详细（不常用）信息表
 * </p>
 *
 * @author tedu.cn
 * @since 2022-02-22
 */
@Data
public class UserDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 生日
     */
    private LocalDate dayOfBirth;

    /**
     * 国家
     */
    private String country;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区
     */
    private String district;

    /**
     * 学历
     */
    private String education;

    /**
     * 行业
     */
    private String industry;

    /**
     * 职业
     */
    private String career;
    /**
     * 性别 1=男 0=女
     */
    private Integer gender;
    /**
     * 数据创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 数据最后修改时间
     */
    private LocalDateTime gmtModified;

}
