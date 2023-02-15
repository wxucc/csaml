package cn.tedu.mall.pojo.product.dto;

import cn.tedu.mall.pojo.valid.product.CategoryRegExpression;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
public class CategoryUpdateBaseInfoDTO implements CategoryRegExpression, Serializable {

    /**
     * 验证请求参数失败的描述文本前缀
     */
    private static final String VALIDATE_MESSAGE_PREFIX = "修改类别基本信息失败，";

    /**
     * 类别名称
     */
    @ApiModelProperty(value = "类别名称", position = 1)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请填写名称！")
    @Pattern(regexp = REGEXP_NAME, message = VALIDATE_MESSAGE_PREFIX + MESSAGE_NAME)
    private String name;

    /**
     * 关键词列表，各关键词使用英文的逗号分隔
     */
    @ApiModelProperty(value = "关键词列表，各关键词使用英文的逗号分隔", position = 2)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请填写关键词列表！")
    @Pattern(regexp = REGEXP_KEYWORDS, message = VALIDATE_MESSAGE_PREFIX + MESSAGE_KEYWORDS)
    private String keywords;

    /**
     * 图标图片的URL
     */
    @ApiModelProperty(value = "图标图片的URL", position = 3)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请输入图标图片的URL！")
    @Pattern(regexp = REGEXP_ICON, message = VALIDATE_MESSAGE_PREFIX + MESSAGE_ICON)
    private String icon;

    /**
     * 自定义排序序号
     */
    @ApiModelProperty(value = "自定义排序序号", position = 4)
    @Range(max = 99, message = VALIDATE_MESSAGE_PREFIX + MESSAGE_SORT)
    private Integer sort;

}
