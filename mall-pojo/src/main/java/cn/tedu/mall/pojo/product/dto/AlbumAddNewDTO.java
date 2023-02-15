package cn.tedu.mall.pojo.product.dto;

import cn.tedu.mall.pojo.valid.product.AlbumRegExpression;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
public class AlbumAddNewDTO implements AlbumRegExpression, Serializable {

    /**
     * 验证请求参数失败的描述文本前缀
     */
    private static final String VALIDATE_MESSAGE_PREFIX = "新增相册失败，";

    /**
     * 相册名称
     */
    @ApiModelProperty(value = "商品相册名称", required = true)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请填写相册名称！")
    @Pattern(regexp = REGEXP_NAME, message = VALIDATE_MESSAGE_PREFIX + MESSAGE_NAME)
    private String name;

    /**
     * 相册简介
     */
    @ApiModelProperty(value = "相册简介", required = true)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请填写相册简介！")
    @Pattern(regexp = REGEXP_DESCRIPTION, message = VALIDATE_MESSAGE_PREFIX + MESSAGE_DESCRIPTION)
    private String description;

    /**
     * 自定义排序序号
     */
    @ApiModelProperty(value = "自定义排序序号", required = true)
    @Range(max = 99, message = VALIDATE_MESSAGE_PREFIX + MESSAGE_SORT)
    private Integer sort;

}
