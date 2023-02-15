package cn.tedu.mall.pojo.product.dto;

import cn.tedu.mall.pojo.valid.product.AttributeTemplateRegExpression;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
public class AttributeTemplateAddNewDTO implements AttributeTemplateRegExpression, Serializable {

    /**
     * 验证请求参数失败的描述文本前缀
     */
    private static final String VALIDATE_MESSAGE_PREFIX = "新增属性模板失败，";

    /**
     * 类别id
     */
    @ApiModelProperty(value = "类别id", required = true)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请选择类别！")
    @Min(value = 1, message = VALIDATE_MESSAGE_PREFIX + "选择的类别数据的格式错误！")
    private Long categoryId;

    /**
     * 属性模板名称
     */
    @ApiModelProperty(value = "属性模板名称", required = true)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请填写名称！")
    @Pattern(regexp = REGEXP_NAME, message = VALIDATE_MESSAGE_PREFIX + MESSAGE_NAME)
    private String name;

    /**
     * 属性模板名称的拼音
     */
    @ApiModelProperty(value = "属性模板名称的拼音", required = true)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请填写名称的拼音！")
    @Pattern(regexp = REGEXP_PINYIN, message = VALIDATE_MESSAGE_PREFIX + MESSAGE_PINYIN)
    private String pinyin;

    /**
     * 关键词列表，各关键词使用英文的逗号分隔
     */
    @ApiModelProperty(value = "关键词列表，各关键词使用英文的逗号分隔", required = true)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请填写关键词列表！")
    @Pattern(regexp = REGEXP_KEYWORDS, message = VALIDATE_MESSAGE_PREFIX + MESSAGE_KEYWORDS)
    private String keywords;

    /**
     * 自定义排序序号
     */
    @ApiModelProperty(value = "自定义排序序号")
    @Range(max = 99, message = VALIDATE_MESSAGE_PREFIX + MESSAGE_SORT)
    private Integer sort;

}
