package cn.tedu.mall.pojo.product.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class PictureSimpleVO implements Serializable {

    /**
     * 图片url
     */
    @ApiModelProperty(value = "图片url", position = 1)
    private String url;

    /**
     * 图片宽度，单位：px
     */
    @ApiModelProperty(value = "图片宽度，单位：px", position = 2)
    private Integer width;

    /**
     * 图片高度，单位：px
     */
    @ApiModelProperty(value = "图片高度，单位：px", position = 3)
    private Integer height;

}
