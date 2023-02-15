package cn.tedu.mall.pojo.ums.dto;

import cn.tedu.mall.pojo.valid.ums.UserRegistryRegExpression;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@ApiModel(value="用户注册DTO")
@Data
public class UserRegistryDTO implements UserRegistryRegExpression, Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 验证注册用户失败前缀
     */
    private static final String VALIDATE_MESSAGE_PREFIX = "注册用户失败，";

    @ApiModelProperty(value="用户名",name="username",required = true,example = "chachabangzhu")
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请填写用户名！")
    @Pattern(regexp = REGEXP_USER_USERNAME, message = VALIDATE_MESSAGE_PREFIX + MESSAGE_USER_USERNAME)
    private String username;
    @ApiModelProperty(value="昵称",name="nickname",required = true,example = "帮主大人")
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请填写用户名！")
    @Pattern(regexp = REGEXP_USER_NICKNAME, message = VALIDATE_MESSAGE_PREFIX + MESSAGE_USER_NICKNAME)
    private String nickname;
    @ApiModelProperty(value="用户邮箱",name="email",required = true,example = "chachabang@tedu.cn")
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请填写邮箱！")
    @Pattern(regexp = REGEXP_USER_EMAIL, message = VALIDATE_MESSAGE_PREFIX + MESSAGE_USER_EMAIL)
    private String email;
    @ApiModelProperty(value="移动电话",name="phone",required = true,example = "18511100998")
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请填写手机号！")
    @Pattern(regexp = REGEXP_USER_PHONE, message = VALIDATE_MESSAGE_PREFIX + MESSAGE_USER_PHONE)
    private String phone;
    @ApiModelProperty(value="密码",name="password",required = true,example = "tarena2017Up")
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请填写密码！")
    @Pattern(regexp = REGEXP_USER_PASSWORD, message = VALIDATE_MESSAGE_PREFIX + MESSAGE_USER_PASSWORD)
    private String password;
    @ApiModelProperty(value="确认密码",name="ackPassword",required = true,example = "tarena2017Up")
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请填写确认密码！")
    @Pattern(regexp = REGEXP_USER_PASSWORD, message = VALIDATE_MESSAGE_PREFIX + MESSAGE_USER_PASSWORD)
    private String ackPassword;

}
