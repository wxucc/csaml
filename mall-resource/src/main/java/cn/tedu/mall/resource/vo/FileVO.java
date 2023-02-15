package cn.tedu.mall.resource.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class FileVO {

    /**
     * 文件路径
     */
    @ApiModelProperty(value = "文件路径", position = 1)
    private String url;

    /**
     * 文件类型
     */
    @ApiModelProperty(value = "文件类型", position = 2)
    private String contentType;

    /**
     * 文件大小，单位：字节
     */
    @ApiModelProperty(value = "文件大小，单位：字节", position = 3)
    private Long size;

}
