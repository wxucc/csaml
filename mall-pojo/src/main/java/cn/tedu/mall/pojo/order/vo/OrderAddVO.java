package cn.tedu.mall.pojo.order.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@ApiModel(value="订单新增VO数据")
@Data
public class OrderAddVO implements Serializable {
    @ApiModelProperty(value="订单id",name="id",example = "1")
    private Long id;
    @ApiModelProperty(value="订单编号",name="sn",example = "sn")
    private String sn;
    @ApiModelProperty(value="创建时间",name="createTime",example ="2022-03-31 10:32:22")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime createTime;
    @ApiModelProperty(value="需要支付金额",name="payAmount",example ="5000")
    private BigDecimal payAmount;

}
