package cn.tedu.mall.pojo.front.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@ApiModel(value="前台SPU关联属性")
@Data
public class FrontAttributeSimpleVO implements Serializable {

    /**
     * 属性id
     */
    @ApiModelProperty(value = "属性id")
    private Long id;

    /**
     * 属性名称
     */
    @ApiModelProperty(value = "属性名称")
    private String name;

    /**
     * 所属属性模版id
     */
    @ApiModelProperty(value = "关联模板id")
    private Long attributeTemplateId;

    /**
     * 简介（某些属性名称可能相同，通过简介补充描述）
     */
    @ApiModelProperty(value = "简介（某些属性名称可能相同，通过简介补充描述）")
    private String description;

    /**
     * 属性类型，1=销售属性，0=非销售属性
     */
    @ApiModelProperty(value = "属性类型", notes = "1=销售属性，0=非销售属性")
    private Integer type;

    /**
     * 输入类型，0=手动录入，1=单选，2=多选， 3=单选（下拉列表），4=多选（下拉列表）
     */
    @ApiModelProperty(value = "输入类型", notes = "0=手动录入，1=单选，2=多选， 3=单选（下拉列表），4=多选（下拉列表）")
    private Integer inputType;

    /**
     * 备选值列表
     */
    @ApiModelProperty(value = "备选值列表")
    private String valueList;

    /**
     * 自动补充的计量单位
     */
    @ApiModelProperty(value = "自动补充的计量单位")
    private String unit;

    /**
     * 自定义排序序号
     */
    @ApiModelProperty(value = "自定义排序序号")
    private Integer sort;

    /**
     * 是否允许自定义，1=允许，0=禁止
     */
    @ApiModelProperty(value = "否允许自定义", notes = "1=允许，0=禁止")
    private Integer isAllowCustomize;

    /**
     * 数据创建时间
     */
    @ApiModelProperty(value = "数据创建时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime gmtCreate;

    /**
     * 数据最后修改时间
     */
    @ApiModelProperty(value = "数据最后修改时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime gmtModified;

    @ApiModelProperty(value = "是否影响图片分组", notes = "0=不影响，1=影响")
    private Integer isEffectPicture;
}
