package cn.tedu.mall.pojo.product.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
public class PictureUploadSingleDTO implements Serializable {

    /**
     * 业务头
     */
    @ApiModelProperty(value = "业务头，用来区分业务模块从而分开文件夹处理", required = true, example = "product,category,user")
    private String head;

    /**
     * 张图片
     */
    @ApiModelProperty(value = "单张图片", dataType = "__File", required = true)
    private MultipartFile picture;

}
