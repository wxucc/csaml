package cn.tedu.mall.pojo.product.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class PictureAddNewBatchDTO implements Serializable {

    /**
     * 验证请求参数失败的描述文本前缀
     */
    private static final String VALIDATE_MESSAGE_PREFIX = "新增图片信息失败，";

    /**
     * 图片所属相册id
     */
    @ApiModelProperty(value = "图片所属相册id", required = true, dataType = "long")
    private Long albumId;

    /**
     * 图片URL
     */
    @ApiModelProperty(value = "图片URL", required = true)
    private String url;

    /**
     * 图片简介
     */
    @ApiModelProperty(value = "商品图片简介", required = true)
    private String description;

}
