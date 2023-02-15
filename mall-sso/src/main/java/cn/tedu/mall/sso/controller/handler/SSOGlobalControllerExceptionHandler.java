/*
package cn.tedu.mall.sso.controller.handler;

import cn.tedu.mall.common.exception.CoolSharkServiceException;
import cn.tedu.mall.common.restful.JsonResult;
import cn.tedu.mall.common.restful.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

*/
/**
 * <p>全局异常处理器</p>
 *//*

@RestControllerAdvice(basePackages = "cn.tedu.mall.sso.controller")
@Slf4j
public class SSOGlobalControllerExceptionHandler {
    */
/**
     * 可控异常,自定义异常
     *//*

    @ExceptionHandler({CoolSharkServiceException.class})
    public JsonResult handleCoolSharkServiceException(CoolSharkServiceException e) {
        log.info("Error! code={}, message={}", e.getResponseCode().getValue(), e.getMessage(),e);
        return JsonResult.failed(e);
    }

    */
/**
     * 系统异常
     *//*

    @ExceptionHandler({Throwable.class})
    public JsonResult handleSystemError(Throwable e) {
        log.info("Error! message={}", e.getMessage(),e);
        return JsonResult.failed(ResponseCode.INTERNAL_SERVER_ERROR, e);
    }

}*/
