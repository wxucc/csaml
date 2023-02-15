package cn.tedu.mall.seckill.exception;

import cn.tedu.mall.common.restful.JsonResult;
import cn.tedu.mall.common.restful.ResponseCode;
import cn.tedu.mall.pojo.seckill.dto.SeckillOrderAddDTO;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;

// 秒杀业务限流异常处理类
@Slf4j
public class SeckillBlockHandler {

    // 声明限流方法,返回值必须和控制器方法一致
    // 参数要包含全部的控制器方法参数,在最后再额外添加BlockException
    // 如果在其他类中声明限流方法,要求限流方法必须是static修饰的

    public static JsonResult seckillBlock(String randCode,
                                          SeckillOrderAddDTO seckillOrderAddDTO,
                                          BlockException e){
        log.error("一个请求被限流了!");
        return JsonResult.failed(
                ResponseCode.INTERNAL_SERVER_ERROR,"服务器忙,请稍候再试");
    }
}
