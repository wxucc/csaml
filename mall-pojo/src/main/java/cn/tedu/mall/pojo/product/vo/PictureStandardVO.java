package cn.tedu.mall.pojo.product.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class PictureStandardVO implements Serializable {

    /**
     * 图片id
     */
    @ApiModelProperty(value = "图片id", position = 1)
    private Long id;

    /**
     * 相册id
     */
    @ApiModelProperty(value = "图片所属的相册id", position = 2)
    private Long albumId;

    /**
     * 图片url
     */
    @ApiModelProperty(value = "图片url", position = 3)
    private String url;

    /**
     * 是否为封面图片，1=是，0=否
     */
    @ApiModelProperty(value = "是否为封面，1=是，0=否", position = 4)
    private Integer cover;

    /**
     * 图片简介
     */
    @ApiModelProperty(value = "图片简介", position = 5)
    private String description;

    /**
     * 图片宽度，单位：px
     */
    @ApiModelProperty(value = "图片宽度，单位：px", position = 6)
    private Integer width;

    /**
     * 图片高度，单位：px
     */
    @ApiModelProperty(value = "图片高度，单位：px", position = 7)
    private Integer height;

    /**
     * 自定义排序序号
     */
    @ApiModelProperty(value = "自定义排序序号", position = 8)
    private Integer sort;

}
