package cn.tedu.mall.pojo.product.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class AlbumStandardVO implements Serializable {

    /**
     * 相册id
     */
    @ApiModelProperty(value = "相册id", position = 1)
    private Long id;

    /**
     * 相册名称
     */
    @ApiModelProperty(value = "相册名称", position = 2)
    private String name;

    /**
     * 相册简介
     */
    @ApiModelProperty(value = "相册简介", position = 3)
    private String description;

    /**
     * 自定义排序序号
     */
    @ApiModelProperty(value = "自定义排序序号", position = 4)
    private Integer sort;

}
