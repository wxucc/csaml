package cn.tedu.mall.pojo.product.dto;

import cn.tedu.mall.pojo.valid.product.AttributeRegExpression;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
public class AttributeUpdateDTO implements AttributeRegExpression, Serializable {

    /**
     * 验证请求参数失败的描述文本前缀
     */
    private static final String VALIDATE_MESSAGE_PREFIX = "修改属性失败，";

    /**
     * 所属属性模板id
     */
    @ApiModelProperty(value = "所属属性模板id", required = true)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请选择所属属性模板！")
    @Min(value = 1, message = VALIDATE_MESSAGE_PREFIX + "选择的所属属性模板的数据格式错误！")
    private Long templateId;

    /**
     * 属性名称
     */
    @ApiModelProperty(value = "属性名称", required = true)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请填写名称！")
    @Pattern(regexp = REGEXP_NAME, message = VALIDATE_MESSAGE_PREFIX + MESSAGE_NAME)
    private String name;

    /**
     * 简介（某些属性名称可能相同，通过简介补充描述）
     */
    @ApiModelProperty(value = "简介（某些属性名称可能相同，通过简介补充描述）", required = true)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请填写简介！")
    @Pattern(regexp = REGEXP_DESCRIPTION, message = VALIDATE_MESSAGE_PREFIX + MESSAGE_DESCRIPTION)
    private String description;

    /**
     * 属性类型，1=销售属性，0=非销售属性
     */
    @ApiModelProperty(value = "属性类型，1=销售属性，0=非销售属性", required = true)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请选择类型！")
    @Range(max = 1, message = VALIDATE_MESSAGE_PREFIX + "选择的属性类型的数据格式错误！")
    private Integer type;

    /**
     * 属性值输入类型，0=手动录入，1=单选，2=多选， 3=单选（下拉列表），4=多选（下拉列表）
     */
    @ApiModelProperty(value = "属性值输入类型，0=手动录入，1=单选，2=多选， 3=单选（下拉列表），4=多选（下拉列表）", required = true)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请选择属性值输入类型！")
    @Range(max = 4, message = VALIDATE_MESSAGE_PREFIX + "选择的属性值输入类型的数据格式错误！")
    private Integer inputType;

    /**
     * 备选值列表
     */
    @ApiModelProperty(value = "备选值列表")
    @Pattern(regexp = REGEXP_VALUE_LIST, message = VALIDATE_MESSAGE_PREFIX + MESSAGE_VALUE_LIST)
    private String valueList;

    /**
     * 计件量位
     */
    @ApiModelProperty(value = "计量单位")
    @Pattern(regexp = REGEXP_UNIT, message = VALIDATE_MESSAGE_PREFIX + MESSAGE_UNIT)
    private String unit;

    /**
     * 自定义排序序号
     */
    @ApiModelProperty(value = "自定义排序序号")
    @Range(max = 99, message = VALIDATE_MESSAGE_PREFIX + MESSAGE_SORT)
    private Integer sort;

    /**
     * 是否允许自定义，1=允许，0=禁止
     */
    @ApiModelProperty(value = "是否允许自定义，1=允许，0=禁止")
    @Range(max = 1, message = VALIDATE_MESSAGE_PREFIX + "选择的是否允许自定义的数据格式错误！")
    private Integer allowCustomize;

}
