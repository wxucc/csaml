package cn.tedu.mall.pojo.product.dto;

import cn.tedu.mall.pojo.valid.product.PictureRegExpression;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
public class PictureAddNewDTO implements PictureRegExpression, Serializable {

    /**
     * 验证请求参数失败的描述文本前缀
     */
    private static final String VALIDATE_MESSAGE_PREFIX = "新增图片信息失败，";

    /**
     * 所属相册id
     */
    @ApiModelProperty(value = "图片所属相册id", required = true, dataType = "long")
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请选择所属相册！")
    @Min(value = 1, message = VALIDATE_MESSAGE_PREFIX + "选择的所属相册的数据格式错误！")
    private Long albumId;

    /**
     * URL
     */
    @ApiModelProperty(value = "图片URL", required = true)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请填写URL！")
    @Pattern(regexp = REGEXP_URL, message = VALIDATE_MESSAGE_PREFIX + MESSAGE_URL)
    private String url;

    /**
     * 简介
     */
    @ApiModelProperty(value = "简介", required = true)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请填写简介！")
    @Pattern(regexp = REGEXP_DESCRIPTION, message = VALIDATE_MESSAGE_PREFIX + MESSAGE_DESCRIPTION)
    private String description;

    /**
     * 宽度，单位：px
     */
    @ApiModelProperty(value = "宽度，单位：px", required = true)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请填写宽度（单位：px）！")
    @Range(max = 4096, message = VALIDATE_MESSAGE_PREFIX + "宽度必须是0~4096的值！")
    private Integer width;

    /**
     * 图片高度，单位：px
     */
    @ApiModelProperty(value = "图片高度，单位：px", required = true)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请填写高度（单位：px）！")
    @Range(max = 4096, message = VALIDATE_MESSAGE_PREFIX + "高度必须是0~4096的值！")
    private Integer height;

    /**
     * 自定义排序序号
     */
    @ApiModelProperty(value = "自定义排序序号")
    @Range(max = 99, message = VALIDATE_MESSAGE_PREFIX + MESSAGE_SORT)
    private Integer sort;

}
