package cn.tedu.mall.pojo.admin.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@ApiModel(value="权限VO")
@Data
public class PermissionVO implements Serializable {

    @ApiModelProperty(name="权限id")
    private Long id;

    /**
     * 名称
     */
    @ApiModelProperty(name="权限名称")
    private String name;

    /**
     * 值
     */
    @ApiModelProperty(name="权限值")
    private String value;

    /**
     * 描述
     */
    @ApiModelProperty(name="权限描述")
    private String description;

    /**
     * 自定义排序序号
     */
    @ApiModelProperty(name="自定义排序")
    private Integer sort;

    /**
     * 数据创建时间
     */
    @ApiModelProperty(name="创建时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime gmtCreate;

    /**
     * 数据最后修改时间
     */
    @ApiModelProperty(name="更新时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime gmtModified;

}
