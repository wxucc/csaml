package cn.tedu.mall.resource.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ImageFileVO extends FileVO {

    /**
     * 图片宽度，单位：px
     */
    @ApiModelProperty(value = "图片宽度，单位：px", position = 4)
    private Integer width;

    /**
     * 图片高度，单位：px
     */
    @ApiModelProperty(value = "图片高度，单位：px", position = 5)
    private Integer height;

}
