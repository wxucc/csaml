package cn.tedu.mall.pojo.front.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(value="前台SPU DETAIL")
@Data
public class FrontSpuDetailVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 数据id
     */
    @ApiModelProperty("数据id")
    private Long id;

    /**
     * SPU id
     */
    @ApiModelProperty("SPU id")
    private Long spuId;

    /**
     * SPU详情，应该使用HTML富文本，通常内容是若干张图片
     */
    @ApiModelProperty("SPU详情，应该使用HTML富文本，通常内容是若干张图片")
    private String detail;
}
