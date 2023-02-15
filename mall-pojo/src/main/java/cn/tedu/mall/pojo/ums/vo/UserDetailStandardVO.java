package cn.tedu.mall.pojo.ums.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
public class UserDetailStandardVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 生日
     */
    @JsonFormat(pattern="yyyy-MM-dd")
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
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime gmtCreate;

    /**
     * 数据最后修改时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime gmtModified;
}
