package cn.tedu.mall.pojo.product.dto;

import cn.tedu.mall.pojo.valid.product.SkuRegExpression;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class SkuUpdateFullInfoDTO implements SkuRegExpression, Serializable {

    /**
     * 验证请求参数失败的描述文本前缀
     */
    private static final String VALIDATE_MESSAGE_PREFIX = "修改SKU失败，";

    /**
     * 单价
     */
    @ApiModelProperty(value = "单价")
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请填写单价！")
    @Digits(integer = 10, fraction = 2, message = "单价的格式错误，整数位数最多10位，小数位数最多2位！")
    private BigDecimal price;

    /**
     * 当前库存
     */
    @ApiModelProperty(value = "当前库存")
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请填写当前库存！")
    @Range(min = 1, max = 9999999, message = "当前库存必须是1~999999的值！")
    private Integer stock;

    /**
     * 库存预警阈值
     */
    @ApiModelProperty(value = "库存预警阈值")
    @NotNull(message = VALIDATE_MESSAGE_PREFIX + "请填写库存预警阈值！")
    @Range(min = 1, max = 9999999, message = "库存预警阈值必须是1~999999的值！")
    private Integer stockThreshold;

    /**
     * 自定义排序序号
     */
    @ApiModelProperty(value = "自定义排序序号")
    @Range(max = 99, message = VALIDATE_MESSAGE_PREFIX + MESSAGE_SORT)
    private Integer sort;

}
