package cn.tedu.mall.sso.pojo.dto;

import cn.tedu.mall.common.validation.RegExpressions;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * <p>后台管理账户登录的DTO模板</p>
 *
 * <p>2021-12-14</p>
 * <ul>
 *     <li>增加Validation验证</li>
 * </ul>
 */
@ApiModel(value = "后台登录的DTO")
@Data
public class AdminLoginDTO implements RegExpressions {

    /**
     * 验证不通过时的提示文本的前缀
     */
    private static final String MESSAGE_PREFIX = "登录失败！";

    @ApiModelProperty(value = "登录用户名", name = "username", example = "root", required = true)
    @NotNull(message = MESSAGE_PREFIX + "用户名不允许为空！")
    @Pattern(regexp = REGEXP_ADMIN_USERNAME, message = MESSAGE_PREFIX + MESSAGE_ADMIN_USERNAME)
    private String username;

    @ApiModelProperty(value = "登录密码", name = "password", example = "123456", required = true)
    @NotNull(message = MESSAGE_PREFIX + "密码不允许为空！")
    @Pattern(regexp = REGEXP_ADMIN_PASSWORD, message = MESSAGE_PREFIX + MESSAGE_ADMIN_PASSWORD)
    private String password;

    @ApiModelProperty(value = "重定向url", name = "redirectUrl", example = "http://csmall.net.cn")
    private String redirectUrl;

    @ApiModelProperty(value = "state随机号防止csrf攻击", name = "state", example = "state")
    private String state;

    @ApiModelProperty(value = "客户端登录ip,后台填写", name = "ip", example = "127.0.0.1",required = false)
    private String ip;

    @ApiModelProperty(value = "用户代理,后台填写", name = "userAgent", example = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/15.0 Safari/605.1.15",required = false)
    private String userAgent;

}
