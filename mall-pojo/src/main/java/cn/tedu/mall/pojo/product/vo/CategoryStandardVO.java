package cn.tedu.mall.pojo.product.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryStandardVO implements Serializable {

    /**
     * 类别id
     */
    @ApiModelProperty(value = "类别id", position = 1)
    private Long id;

    /**
     * 父级类别id，如果无父级，则为0
     */
    @ApiModelProperty(value = "父级类别id，如果无父级，则为0", position = 2)
    private Long parentId;

    /**
     * 类别名称
     */
    @ApiModelProperty(value = "类别名称", position = 3)
    private String name;

    /**
     * 深度，最顶级类别的深度为1，次级为2，以此类推
     */
    @ApiModelProperty(value = "深度，最顶级类别的深度为1，次级为2，以此类推", position = 4)
    private Integer depth;

    /**
     * 关键词列表，各关键词使用英文的逗号分隔
     */
    @ApiModelProperty(value = "关键词列表，各关键词使用英文的逗号分隔", position = 5)
    private String keywords;

    /**
     * 图标图片的URL
     */
    @ApiModelProperty(value = "图标图片的URL", position = 6)
    private String icon;

    /**
     * 是否启用，1=启用，0=未启用
     */
    @ApiModelProperty(value = "是否启用，1=启用，0=未启用", position = 7)
    private Integer enable;

    /**
     * 是否为父级（是否包含子级），1=是父级，0=不是父级
     */
    @ApiModelProperty(value = "是否为父级（是否包含子级），1=是父级，0=不是父级", position = 8)
    private Integer parent;

    /**
     * 是否显示在导航栏中，1=启用，0=未启用
     */
    @ApiModelProperty(value = "是否显示在导航栏中，1=启用，0=未启用", position = 9)
    private Integer display;

    /**
     * 自定义排序序号
     */
    @ApiModelProperty(value = "自定义排序序号", position = 10)
    private Integer sort;



}
