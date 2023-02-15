package cn.tedu.mall.pojo.admin.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value="权限新增DTO")
@Data
public class PermissionAddDTO implements Serializable {
    /**
     * 名称
     */

    @ApiModelProperty(value="权限名称",
            notes = "权限职责描述",
            required = true)
    private String name;

    /**
     * 值
     */

    @ApiModelProperty(value="权限值",
            notes = "用来控制权限的具体值",
            required = true)
    private String value;

    /**
     * 描述
     */

    @ApiModelProperty(value="权限描述",
            required = true)
    private String description;

    /**
     * 自定义排序序号
     */

    @ApiModelProperty(value="权限自定义排序",
            notes = "权限自定义排序",
            required = false)
    private Integer sort;
}
