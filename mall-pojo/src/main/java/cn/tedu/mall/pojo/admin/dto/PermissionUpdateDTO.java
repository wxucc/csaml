package cn.tedu.mall.pojo.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


@ApiModel(value="权限编辑DTO")
@Data
public class PermissionUpdateDTO implements Serializable {

    @ApiModelProperty(value="权限id",required = true)
    private Long id;

    /**
     * 名称
     */

    @ApiModelProperty(value="权限名称")
    private String name;

    /**
     * 值
     */

    @ApiModelProperty(value="权限值")
    private String value;

    /**
     * 描述
     */

    @ApiModelProperty(value="权限描述")
    private String description;

    /**
     * 自定义排序序号
     */

    @ApiModelProperty(value="自定义排序")
    private Integer sort;
}
