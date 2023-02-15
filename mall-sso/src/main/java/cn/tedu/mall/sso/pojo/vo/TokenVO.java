package cn.tedu.mall.sso.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "Token模板")
@Data
public class TokenVO {

    @ApiModelProperty(value = "JWT Token值")
    private String tokenValue;

    @ApiModelProperty(value = "携带Token的头",example = "Bearer")
    private String tokenHeader;

}
