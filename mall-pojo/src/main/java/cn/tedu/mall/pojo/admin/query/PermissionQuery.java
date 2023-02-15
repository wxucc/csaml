package cn.tedu.mall.pojo.admin.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value="权限查询条件")
@Data
public class PermissionQuery implements Serializable {
    @ApiModelProperty(value="模糊资源名称")
    private String name;
    @ApiModelProperty(value="模糊资源路径")
    private String value;
    @ApiModelProperty(value="页数",required = true)
    private Integer pageNum;
    @ApiModelProperty(value="条数",required = true)
    private Integer sizeNum;
}
