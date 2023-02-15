package cn.tedu.mall.pojo.product.dto;

import cn.tedu.mall.pojo.valid.product.BrandRegExpression;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
public class BrandAddNewDTO implements BrandRegExpression, Serializable {

    /**
     * 验证请求参数失败的描述文本前缀
     */
    private static final String VALIDATE_MESSAGE_PREFIX = "新增品牌失败，";

    /**
     * 品牌名称
     */
    @ApiModelProperty(value = "品牌名称", required = true)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请填写名称！")
    @Pattern(regexp = REGEXP_NAME, message = VALIDATE_MESSAGE_PREFIX + MESSAGE_NAME)
    private String name;

    /**
     * 品牌名称的拼音
     */
    @ApiModelProperty(value = "品牌名称的拼音", required = true)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请填写名称的拼音！")
    @Pattern(regexp = REGEXP_PINYIN, message = VALIDATE_MESSAGE_PREFIX + MESSAGE_PINYIN)
    private String pinyin;

    /**
     * 品牌Logo的URL
     */
    @ApiModelProperty(value = "品牌Logo的URL", required = true)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请输入Logo的URL！")
    @Pattern(regexp = REGEXP_LOGO, message = VALIDATE_MESSAGE_PREFIX + MESSAGE_LOGO)
    private String logo;

    /**
     * 品牌简介
     */
    @ApiModelProperty(value = "品牌简介", required = true)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请填写简介！")
    @Pattern(regexp = REGEXP_DESCRIPTION, message = VALIDATE_MESSAGE_PREFIX + MESSAGE_DESCRIPTION)
    private String description;

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
    @Range(max = 99, message = MESSAGE_SORT)
    private Integer sort;

    /**
     * 是否启用，1=启用，0=未启用
     */
    @ApiModelProperty(value = "是否启用", notes = "1=启用，0=未启用", required = true)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请选择是否启用！")
    @Range(max = 1, message = VALIDATE_MESSAGE_PREFIX + MESSAGE_ENABLE)
    private Integer enable;

}
