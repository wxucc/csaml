package cn.tedu.mall.pojo.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value="角色新增DTO")
@Data
public class RoleAddDTO implements Serializable {
    /**
     * 名称
     */

    @ApiModelProperty(value="角色名称",required = true)
    private String name;

    /**
     * 描述
     */

    @ApiModelProperty(value="描述",required = true)
    private String description;

    /**
     * 自定义排序序号
     */

    @ApiModelProperty(value="自定义排序序号",required = true)
    private Integer sort;
}
