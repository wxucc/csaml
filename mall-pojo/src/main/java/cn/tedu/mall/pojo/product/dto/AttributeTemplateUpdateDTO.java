package cn.tedu.mall.pojo.product.dto;

import cn.tedu.mall.pojo.valid.product.AttributeTemplateRegExpression;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
public class AttributeTemplateUpdateDTO implements AttributeTemplateRegExpression, Serializable {

    /**
     * 验证请求参数失败的描述文本前缀
     */
    private static final String VALID_MESSAGE_PREFIX = "修改属性模板失败，";

    /**
     * 属性模板名称
     */
    @ApiModelProperty(value = "属性模板名称", required = true)
    @NotNull(message = VALID_MESSAGE_PREFIX + "请填写名称！")
    @Pattern(regexp = REGEXP_NAME, message = VALID_MESSAGE_PREFIX + MESSAGE_NAME)
    private String name;

    /**
     * 属性模板名称的拼音
     */
    @ApiModelProperty(value = "属性模板名称的拼音", required = true)
    @NotNull(message = VALID_MESSAGE_PREFIX + "请填写名称的拼音！")
    @Pattern(regexp = REGEXP_PINYIN, message = VALID_MESSAGE_PREFIX + MESSAGE_PINYIN)
    private String pinyin;

    /**
     * 关键词列表，各关键词使用英文的逗号分隔
     */
    @ApiModelProperty(value = "关键词列表，各关键词使用英文的逗号分隔", required = true)
    @NotNull(message = VALID_MESSAGE_PREFIX + "请填写关键词列表！")
    @Pattern(regexp = REGEXP_KEYWORDS, message = VALID_MESSAGE_PREFIX + MESSAGE_KEYWORDS)
    private String keywords;

    /**
     * 自定义排序序号
     */
    @ApiModelProperty(value = "自定义排序序号", required = true)
    @Range(max = 99, message = VALID_MESSAGE_PREFIX + MESSAGE_SORT)
    private Integer sort;

}
