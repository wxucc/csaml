package cn.tedu.mall.pojo.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value="角色编辑DTO")
@Data
public class RoleUpdateDTO implements Serializable {
    @ApiModelProperty(value="角色id",required = true)
    private Long id;
    /**
     * 名称
     */

    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 自定义排序序号
     */
    private Integer sort;
}
