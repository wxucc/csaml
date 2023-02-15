package cn.tedu.mall.pojo.product.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class SpuDetailStandardVO implements Serializable {

    /**
     * 数据id
     */
    @ApiModelProperty(value = "数据id", position = 1)
    private Long id;

    /**
     * SPU id
     */
    @ApiModelProperty(value = "SPU id", position = 2)
    private Long spuId;

    /**
     * SPU详情，应该使用HTML富文本，通常内容是若干张图片
     */
    @ApiModelProperty(value = "SPU详情，应该使用HTML富文本，通常内容是若干张图片", position = 3)
    private String detail;

}
