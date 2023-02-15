package cn.tedu.mall.pojo.product.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class AlbumAddNewResultVO implements Serializable {

    /**
     * 相册id
     */
    @ApiModelProperty(value = "相册id", position = 1)
    private Long id;

}
