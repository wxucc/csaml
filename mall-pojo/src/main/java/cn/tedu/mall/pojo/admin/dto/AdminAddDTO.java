package cn.tedu.mall.pojo.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

//import javax.validation.constraints.NotNull;
@Data
@ApiModel(value="后台账号新增DTO")
public class AdminAddDTO implements Serializable {
    /**
     * 用户名
     */
    @ApiModelProperty(value="管理员用户名",required = false)
    //@NotNull(message = "请提交用户名")
    private String username;

    /**
     * 昵称
     */
    @ApiModelProperty(value="管理员昵称")
    private String nickname;

    @ApiModelProperty(value="用户密码",required = true)
   // @NotNull(message = "请提交密码")
    private String password;

    @ApiModelProperty(value="用户确认密码",required = true)
    //@NotNull(message = "请提交确认密码")
    private String passwordAct;
    /**
     * 头像URL
     */
    @ApiModelProperty(value="管理员头像url")
    private String avatar;

    /**
     * 手机号码
     */
    @ApiModelProperty(value="管理员手机号")
    //@NotNull(message = "请提交手机号")
    private String phone;

    /**
     * 电子邮箱
     */
    @ApiModelProperty(value="管理员电子邮箱")
    private String email;

    /**
     * 描述
     */
    @ApiModelProperty(value="管理员描述")
    private String description;

    /**
     * 是否启用，1=启用，0=未启用
     */
    @ApiModelProperty(value="是否启用，1=启用，0=未启用")
    private Integer isEnable;
}
