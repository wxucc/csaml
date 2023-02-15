package cn.tedu.mall.seckill.exception;

import cn.tedu.mall.common.restful.JsonResult;
import cn.tedu.mall.common.restful.ResponseCode;
import cn.tedu.mall.pojo.seckill.dto.SeckillOrderAddDTO;
import lombok.extern.slf4j.Slf4j;

// 秒杀业务降级处理类
@Slf4j
public class SeckillFallBack {

    // 降级方法编写规则基本和限流方法一致,只是最后的参数类型更换为Throwable
    public static JsonResult seckillFallBack(String randCode,
                                             SeckillOrderAddDTO seckillOrderAddDTO,
                                             Throwable e){
        // 输出异常信息,以便调试和找错
        e.printStackTrace();
        log.error("一个请求发送了降级");
        return JsonResult.failed(
                ResponseCode.INTERNAL_SERVER_ERROR,
                "发生异常,异常信息为:"+e.getMessage());
    }

}
