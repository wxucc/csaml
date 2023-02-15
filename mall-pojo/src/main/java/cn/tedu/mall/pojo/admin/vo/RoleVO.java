package cn.tedu.mall.pojo.admin.vo;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@ApiModel(value="角色VO")
@Data
public class RoleVO implements Serializable {

    @ApiModelProperty(value="角色id")
    private Long id;

    /**
     * 名称
     */

    @ApiModelProperty(value="角色名称")
    private String name;

    /**
     * 描述
     */

    @ApiModelProperty(value="角色简介")
    private String description;

    /**
     * 数据创建时间
     */

    @ApiModelProperty(value="创建时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime gmtCreate;

    /**
     * 数据最后修改时间
     */

    @ApiModelProperty(value="修改时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime gmtModified;
}
