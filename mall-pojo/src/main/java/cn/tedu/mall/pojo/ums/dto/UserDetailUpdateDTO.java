package cn.tedu.mall.pojo.ums.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 新注册用户补充用户详细信息
 */
@Data
public class UserDetailUpdateDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @ApiModelProperty(value="用户详情id",name="id",example = "3")
    private Long id;
    /**
     * 生日
     */
    @ApiModelProperty(
            value="生日，格式yyyy-MM-dd",
            name="dayOfBirth",
            example = "1999-10-10")
    private LocalDate dayOfBirth;

    /**
     * 国家
     */
    @ApiModelProperty(
            value="国家",
            name="country",
            example = "中国")
    private String country;
    /**
     * 性别 1=男 0=女
     */
    @ApiModelProperty(
            value="性别 1=男 0=女",
            name="gender",
            example = "1")
    private Integer gender;
    /**
     * 省
     */
    @ApiModelProperty(
            value="省",
            name="province",
            example = "北京")
    private String province;

    /**
     * 市
     */
    @ApiModelProperty(
            value="市",
            name="city",
            example = "北京")
    private String city;

    /**
     * 区
     */
    @ApiModelProperty(
            value="区",
            name="district",
            example = "朝阳")
    private String district;

    /**
     * 学历
     */
    @ApiModelProperty(
            value="学历",
            name="education",
            example = "研究生")
    private String education;

    /**
     * 行业
     */
    @ApiModelProperty(
            value="行业",
            name="industry",
            example = "教育")
    private String industry;

    /**
     * 职业
     */
    @ApiModelProperty(
            value="职业",
            name="career",
            example = "老师")
    private String career;
}
