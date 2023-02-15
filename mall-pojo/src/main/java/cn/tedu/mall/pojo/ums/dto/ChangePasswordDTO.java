package cn.tedu.mall.pojo.ums.dto;

import cn.tedu.mall.pojo.valid.ums.ChangePasswordRegExpression;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class ChangePasswordDTO implements ChangePasswordRegExpression {
    private static final String VALIDATE_MESSAGE_PREFIX = "修改密码失败，";
    @ApiModelProperty(value="原密码",name="password",example = "123456")
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请填写密码！")
    @Pattern(regexp = REGEXP_USER_PASSWORD, message = VALIDATE_MESSAGE_PREFIX + MESSAGE_USER_PASSWORD)
    private String password;
    @ApiModelProperty(value="新密码",name="newPassword",example = "654321")
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请填写新密码！")
    @Pattern(regexp = REGEXP_USER_PASSWORD, message = VALIDATE_MESSAGE_PREFIX + MESSAGE_USER_PASSWORD)
    private String newPassword;
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请填写确认密码！")
    @Pattern(regexp = REGEXP_USER_PASSWORD, message = VALIDATE_MESSAGE_PREFIX + MESSAGE_USER_PASSWORD)
    @ApiModelProperty(value="确认新密码",name="ackNewPassword",example = "654321")
    private String ackNewPassword;
    private String ip;
    private String userAgent;
}
