package cn.tedu.mall.pojo.product.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class AttributeDetailsVO implements Serializable {

    /**
     * 属性id
     */
    @ApiModelProperty(value = "属性id", position = 1)
    private Long id;

    /**
     * 属性名称
     */
    @ApiModelProperty(value = "属性名称", position = 2)
    private String name;

    /**
     * 属性简介（某些属性名称可能相同，通过简介补充描述）
     */
    @ApiModelProperty(value = "属性简介", position = 3)
    private String description;

    /**
     * 属性类型，1=销售属性，0=非销售属性
     */
    @ApiModelProperty(value = "属性类型，1=销售属性，0=非销售属性", position = 4)
    private Integer type;

    /**
     * 属性输入类型，0=手动录入，1=单选，2=多选， 3=单选（下拉列表），4=多选（下拉列表）
     */
    @ApiModelProperty(value = "属性输入类型，0=手动录入，1=单选，2=多选， 3=单选（下拉列表），4=多选（下拉列表）", position = 5)
    private Integer inputType;

    /**
     * 属性备选值列表
     */
    @ApiModelProperty(value = "属性备选值列表", position = 6)
    private String valueList;

    /**
     * 计量单位
     */
    @ApiModelProperty(value = "计量单位", position = 7)
    private String unit;

    /**
     * 自定义排序序号
     */
    @ApiModelProperty(value = "自定义排序序号", position = 8)
    private Integer sort;

    /**
     * 属性是否允许自定义，1=允许，0=禁止
     */
    @ApiModelProperty(value = "属性是否允许自定义，1=允许，0=禁止", position = 9)
    private Integer allowCustomize;

    /**
     * 属性关联到的模板的id
     */
    @ApiModelProperty(value = "属性关联到的模板的id", position = 10)
    private Long attributeTemplateId;

    /**
     * 属性关联到的模板的名称
     */
    @ApiModelProperty(value = "属性关联到的模板的名称", position = 11)
    private String attributeTemplateName;

    /**
     * 属性模板名称的拼音
     */
    @ApiModelProperty(value = "属性模板名称的拼音", position = 12)
    private String attributeTemplatePinyin;

    /**
     * 关键词列表，各关键词使用英文的逗号分隔
     */
    @ApiModelProperty(value = "属性模板关键词列表，各关键词使用英文的逗号分隔", position = 13)
    private String attributeTemplateKeywords;

}
