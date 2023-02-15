package cn.tedu.mall.pojo.product.dto;

import cn.tedu.mall.pojo.valid.product.SkuRegExpression;
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
public class SkuAddNewDTO implements SkuRegExpression, Serializable {

    /**
     * 验证请求参数失败的描述文本前缀
     */
    private static final String VALIDATE_MESSAGE_PREFIX = "新增SKU失败，";

    /**
     * SPU id
     */
    @ApiModelProperty(value = "SPU id", required = true, dataType = "long")
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请选择SPU！")
    @Min(value = 1, message = VALIDATE_MESSAGE_PREFIX + "选择的SPU的数据格式错误！")
    private Long spuId;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题", required = true)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请填写标题！")
    @Pattern(regexp = REGEXP_TITLE, message = VALIDATE_MESSAGE_PREFIX + MESSAGE_TITLE)
    private String title;

    /**
     * 条型码
     */
    @ApiModelProperty(value = "条型码")
    @Pattern(regexp = REGEXP_BAR_CODE, message = VALIDATE_MESSAGE_PREFIX + MESSAGE_BAR_CODE)
    private String barCode;

    /**
     * 属性模板id
     */
    @ApiModelProperty(value = "属性模板id", required = true, dataType = "long")
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请选择属性模板！")
    @Min(value = 1, message = VALIDATE_MESSAGE_PREFIX + "选择的属性模板的数据格式错误！")
    private Long attributeTemplateId;

    /**
     * 所属相册id
     */
    @ApiModelProperty(value = "所属相册id", required = true, dataType = "long")
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请选择所属相册！")
    @Min(value = 1, message = VALIDATE_MESSAGE_PREFIX + "选择的相册的数据格式错误！")
    private Long albumId;

    /**
     * 组图URLs，使用JSON数组格式表示
     */
    @ApiModelProperty(value = "组图URLs，使用JSON数组格式表示", required = true)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请提交图片数据！")
    @Pattern(regexp = REGEXP_PICTURES, message = VALIDATE_MESSAGE_PREFIX + MESSAGE_PICTURES)
    private String pictures;

    /**
     * 单价
     */
    @ApiModelProperty(value = "单价", required = true)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请填写单价！")
    @Digits(integer = 10, fraction = 2, message = "单价的格式错误，整数位数最多10位，小数位数最多2位！")
    private BigDecimal price;

    /**
     * 当前库存
     */
    @ApiModelProperty(value = "当前库存", required = true)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请填写当前库存！")
    @Range(min = 1, max = 9999999, message = "当前库存必须是1~999999的值！")
    private Integer stock;

    /**
     * 库存预警阈值
     */
    @ApiModelProperty(value = "库存预警阈值", required = true)
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请填写库存预警阈值！")
    @Range(min = 1, max = 9999999, message = "库存预警阈值必须是1~999999的值！")
    private Integer stockThreshold;

    /**
     * 【待查】全部属性，使用JSON格式表示（冗余）
     */
    private String specifications;

    /**
     * 自定义排序序号
     */
    @ApiModelProperty(value = "自定义排序序号", name = "sort")
    @Range(max = 99, message = VALIDATE_MESSAGE_PREFIX + MESSAGE_SORT)
    private Integer sort;

}
