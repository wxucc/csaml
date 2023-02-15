package cn.tedu.mall.pojo.seckill.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value="提交秒杀的商品DTO",description = "和购物车类似")
public class SeckillDTO implements Serializable {
    @ApiModelProperty(value="秒杀的skuId",required = true)
    private Long skuId;
    @ApiModelProperty(value="秒杀用户的userId",required = false)
    private Long userId;
}
