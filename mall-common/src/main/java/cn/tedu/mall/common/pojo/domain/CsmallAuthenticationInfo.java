package cn.tedu.mall.common.pojo.domain;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import java.util.List;

/**
 * 自定义认证框架数据封装
 */
@Data
@ApiModel(value="自定义认证框架数据封装")
public class CsmallAuthenticationInfo {
    /**
     * 用户id 可以是admin用户也可以是普通user用户id
     */
    @ApiModelProperty(value="用户id")
    private Long id;
    @ApiModelProperty(value="用户名")
    private String username;
    @ApiModelProperty(value="用户类型")
    private String userType;
    @ApiModelProperty(value="用户权限列表")
    private List<String> authorities;
}
