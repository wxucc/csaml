package cn.tedu.mall.pojo.product.dto;

import cn.tedu.mall.pojo.valid.product.SpuRegExpression;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
public class SpuUpdateDTO implements SpuRegExpression, Serializable {

    /**
     * 验证请求参数失败的描述文本前缀
     */
    private static final String VALIDATE_MESSAGE_PREFIX = "修改SPU失败，";

    /**
     * 类别id
     */
    @ApiModelProperty(value = "类别id", dataType = "long", required = true)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请选择类别！")
    @Min(value = 1, message = VALIDATE_MESSAGE_PREFIX + "选择的类别的数据格式错误！")
    private Long categoryId;

    /**
     * SPU名称
     */
    @ApiModelProperty(value = "SPU名称", example = "iPhone 13")
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请填写名称！")
    @Pattern(regexp = REGEXP_NAME, message = VALIDATE_MESSAGE_PREFIX + MESSAGE_NAME)
    private String name;

    /**
     * 品牌id
     */
    @ApiModelProperty(value = "品牌id", dataType = "long", required = true)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请选择品牌！")
    @Min(value = 1, message = VALIDATE_MESSAGE_PREFIX + "选择的品牌的数据格式错误！")
    private Long brandId;

    /**
     * 属性模板id
     */
    @ApiModelProperty(value = "属性模板id", dataType = "long", required = true)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请选择属性模板！")
    @Min(value = 1, message = VALIDATE_MESSAGE_PREFIX + "选择的属性模板的数据格式错误！")
    private Long attributeTemplateId;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题", required = true)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请填写标题！")
    @Pattern(regexp = REGEXP_TITLE, message = VALIDATE_MESSAGE_PREFIX + MESSAGE_TITLE)
    private String title;

    /**
     * 简介
     */
    @ApiModelProperty(value = "简介", required = true)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请填写简介！")
    @Pattern(regexp = REGEXP_DESCRIPTION, message = VALIDATE_MESSAGE_PREFIX + MESSAGE_DESCRIPTION)
    private String description;

    /**
     * 是否上架（发布）
     */
    @ApiModelProperty(value = "是否上架（发布），1=已上架，0=未上架（下架）", required = true)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请选择是否上架！")
    @Range(max = 1, message = VALIDATE_MESSAGE_PREFIX + "选择的是否上架的数据格式错误！")
    private Integer published;

    /**
     * 是否已审核
     */
    @ApiModelProperty(value = "是否已审核，1=已审核，0=未审核", required = true)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请选择是否已审核！")
    @Range(max = 1, message = VALIDATE_MESSAGE_PREFIX + "选择的是否已审核的数据格式错误！")
    private Integer checked;

    /**
     * SPU编号
     */
    @ApiModelProperty(value = "SPU编号，例如：A2404", required = true)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请填写SPU编号！")
    @Pattern(regexp = REGEXP_TYPE_NUMBER, message = VALIDATE_MESSAGE_PREFIX + MESSAGE_TYPE_NUMBER)
    private String typeNumber;

    /**
     * 是否推荐
     */
    @ApiModelProperty(value = "是否推荐，1=推荐，0=不推荐", required = true)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请选择是否推荐！")
    @Range(max = 1, message = VALIDATE_MESSAGE_PREFIX + "选择的是否推荐的数据格式错误！")
    private Integer recommend;

    /**
     * 是否新品
     */
    @ApiModelProperty(value = "是否新品，1=新品，0=非新品", required = true)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请选择是否新品！")
    @Range(max = 1, message = VALIDATE_MESSAGE_PREFIX + "选择的是否新品的数据格式错误！")
    private Integer newArrival;

    /**
     * 是否标记为删除
     */
    @ApiModelProperty(value = "是否标记为删除，1=已删除，0=未删除", required = true)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请选择是否标记为删除！")
    @Range(max = 1, message = VALIDATE_MESSAGE_PREFIX + "选择的是否标记为已删除的数据格式错误！")
    private Integer deleted;

    /**
     * SPU的详情
     */
    @ApiModelProperty(value = "SPU的详情")
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请填写详情！")
    private String detail;

}
