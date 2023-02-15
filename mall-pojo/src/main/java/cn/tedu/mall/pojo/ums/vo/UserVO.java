package cn.tedu.mall.pojo.ums.vo;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ApiModel(value="前台用户VO")
public class UserVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="前台用户Id",name="id")
    private Long id;

    /**
     * 用户名
     */
    @ApiModelProperty(value="前台用户用户名",name="username")
    private String username;

    /**
     * 昵称
     */
    @ApiModelProperty(value="前台用户昵称",name="nickname")
    private String nickname;

    /**
     * 头像URL
     */
    @ApiModelProperty(value="前台用户头像url",name="avatar")
    private String avatar;

    /**
     * 手机号码
     */
    @ApiModelProperty(value="前台用户手机号",name="phone")
    private String phone;

    /**
     * 电子邮箱
     */
    @ApiModelProperty(value="前台用户邮箱",name="email")
    private String email;

    /**
     * 是否启用，1=启用，0=未启用
     */
    @ApiModelProperty(value="前台用户是否启用,1=启用，0=未启用",name="enable")
    private Integer enable;

    /**
     * 积分（冗余）
     */
    @ApiModelProperty(value="前台用户积分（冗余）",name="rewardPoint")
    private Integer rewardPoint;

    /**
     * 最后登录IP地址（冗余）
     */
    @ApiModelProperty(value="前台用户最后登录IP地址（冗余）",name="lastLoginIp")
    private String lastLoginIp;

    /**
     * 累计登录次数（冗余）
     */
    @ApiModelProperty(value="前台用户累计登录次数（冗余）",name="loginCount")
    private Integer loginCount;

    /**
     * 最后登录时间（冗余）
     */
    @ApiModelProperty(value="前台用户最后登录时间（冗余）",name="gmtLastLogin")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime gmtLastLogin;

    /**
     * 数据创建时间
     */
    @ApiModelProperty(value="前台用户数据创建时间",name="gmtCreate")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime gmtCreate;

    /**
     * 数据最后修改时间
     */
    @ApiModelProperty(value="前台用户数据最后修改时间",name="gmtModified")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime gmtModified;
}
