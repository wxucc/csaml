package cn.tedu.mall.sso.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="获取用户信息VO")
@Data
public class UserInfoVO {
    @ApiModelProperty(value="用户Id",name="userId")
    private Long userId;
    @ApiModelProperty(value="用户名(登录账号)",name="username")
    private String username;
    @ApiModelProperty(value="昵称",name="nickname")
    private String nickname;
    @ApiModelProperty(value="手机号",name="phone")
    private String phone;
}
