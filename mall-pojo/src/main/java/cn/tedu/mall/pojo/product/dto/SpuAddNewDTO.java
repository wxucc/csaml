package cn.tedu.mall.pojo.product.dto;

import cn.tedu.mall.pojo.valid.product.SpuRegExpression;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class SpuAddNewDTO implements SpuRegExpression, Serializable {

    /**
     * 验证请求参数失败的描述文本前缀
     */
    private static final String VALIDATE_MESSAGE_PREFIX = "新增SPU失败，";

    /**
     * SPU名称
     */
    @ApiModelProperty(value = "SPU名称，例如：iPhone 13", required = true)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请填写名称！")
    @Pattern(regexp = REGEXP_NAME, message = VALIDATE_MESSAGE_PREFIX + MESSAGE_NAME)
    private String name;

    /**
     * SPU编号
     */
    @ApiModelProperty(value = "SPU编号，例如：A2404", required = true)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请填写SPU编号！")
    @Pattern(regexp = REGEXP_TYPE_NUMBER, message = VALIDATE_MESSAGE_PREFIX + MESSAGE_TYPE_NUMBER)
    private String typeNumber;

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
     * 价格（显示在列表中）
     */
    @ApiModelProperty(value = "价格（显示在列表中）")
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请填写价格！")
    @Digits(integer = 10, fraction = 2, message = "单价的格式错误，整数位数最多10位，小数位数最多2位！")
    private BigDecimal listPrice;

    /**
     * 当前库存（冗余）
     */
    @ApiModelProperty(value = "当前库存（冗余）")
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请填写当前库存！")
    @Range(min = 1, max = 9999999, message = "当前库存必须是1~999999的值！")
    private Integer stock;

    /**
     * 库存预警阈值（冗余）
     */
    @ApiModelProperty(value = "库存预警阈值（冗余）")
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请填写库存预警阈值！")
    @Range(min = 1, max = 9999999, message = "库存预警阈值必须是1~999999的值！")
    private Integer stockThreshold;

    /**
     * 计件单位
     */
    @ApiModelProperty(value = "计件单位", required = true)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请填写计件单位！")
    @Pattern(regexp = REGEXP_UNIT, message = VALIDATE_MESSAGE_PREFIX + MESSAGE_UNIT)
    private String unit;

    /**
     * 品牌id
     */
    @ApiModelProperty(value = "品牌id", dataType = "long", required = true)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请选择品牌！")
    @Min(value = 1, message = VALIDATE_MESSAGE_PREFIX + "选择的品牌的数据格式错误！")
    private Long brandId;

    /**
     * 类别id
     */
    @ApiModelProperty(value = "类别id", dataType = "long", required = true)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请选择类别！")
    @Min(value = 1, message = VALIDATE_MESSAGE_PREFIX + "选择的类别的数据格式错误！")
    private Long categoryId;

    /**
     * 属性模板id
     */
    @ApiModelProperty(value = "属性模板id", dataType = "long", required = true)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请选择属性模板！")
    @Min(value = 1, message = VALIDATE_MESSAGE_PREFIX + "选择的属性模板的数据格式错误！")
    private Long attributeTemplateId;

    /**
     * 相册id
     */
    @ApiModelProperty(value = "相册id", dataType = "long", required = true)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请选择相册！")
    @Min(value = 1, message = VALIDATE_MESSAGE_PREFIX + "选择的相册的数据格式错误！")
    private Long albumId;

    /**
     * 组图URLs，使用JSON数组表示
     */
    @ApiModelProperty(value = "组图URLs，使用JSON数组表示", required = true)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请提交图片列表！")
    @Pattern(regexp = REGEXP_PICTURES, message = VALIDATE_MESSAGE_PREFIX + MESSAGE_PICTURES)
    private String pictures;

    /**
     * 关键词列表，各关键词使⽤英⽂的逗号分隔
     */
    @ApiModelProperty(value = "关键词列表，各关键词使⽤英⽂的逗号分隔", required = true)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请填写关键词列表！")
    @Pattern(regexp = REGEXP_KEYWORDS, message = VALIDATE_MESSAGE_PREFIX + MESSAGE_KEYWORDS)
    private String keywords;

    /**
     * 标签列表，各标签使⽤英⽂的逗号分隔，原则上最多3个
     */
    @ApiModelProperty(value = "标签列表，各标签使⽤英⽂的逗号分隔，原则上最多3个", required = true)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请填写标签列表！")
    @Pattern(regexp = REGEXP_TAGS, message = VALIDATE_MESSAGE_PREFIX + MESSAGE_TAGS)
    private String tags;

    /**
     * ⾃定义排序序号
     */
    @ApiModelProperty(value = "⾃定义排序序号")
    @Range(max = 99, message = VALIDATE_MESSAGE_PREFIX + MESSAGE_SORT)
    private Integer sort;

    /**
     * SPU的详情
     */
    @ApiModelProperty(value = "SPU的详情", required = true)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请填写详情！")
    private String detail;

}
