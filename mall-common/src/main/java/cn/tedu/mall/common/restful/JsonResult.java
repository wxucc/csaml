package cn.tedu.mall.common.restful;

import cn.tedu.mall.common.exception.CoolSharkServiceException;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 通用响应对象
 */
@Data
public class JsonResult<T> implements Serializable {

    /**
     * 状态码
     */
    @ApiModelProperty(value = "业务状态码", position = 1, example = "200, 400, 401, 403, 404, 409, 500")
    private Integer state;
    /**
     * 消息
     */
    @ApiModelProperty(value = "业务消息", position = 2, example = "登录失败！密码错误！")
    private String message;
    /**
     * 数据
     */
    @ApiModelProperty(value = "业务数据", position = 3)
    private T data;

    /**
     * 创建响应结果对象，表示"成功"，不封装其它任何数据
     *
     * @return 响应结果对象
     */
    public static JsonResult<Void> ok() {
        return ok(null);
    }

    /**
     * 创建响应结果对象，表示"成功"，且封装客户端期望响应的数据
     *
     * @param data 客户端期望响应的数据
     * @return 响应结果对象
     */
    public static <T> JsonResult<T> ok(T data) {
        JsonResult<T> jsonResult = new JsonResult<>();
        jsonResult.setState(ResponseCode.OK.getValue());
        jsonResult.setData(data);
        return jsonResult;
    }

    /**
     * 创建响应结果对象，表示"失败"，且封装"失败"的描述
     *
     * @param e CoolSharkServiceException异常对象
     * @return 响应结果对象
     */
    public static JsonResult<Void> failed(CoolSharkServiceException e) {
        return failed(e.getResponseCode(), e);
    }

    /**
     * 创建响应结果对象，表示"失败"，且封装"失败"的描述
     *
     * @param responseCode "失败"的状态码
     * @param e            "失败"时抛出的异常对象
     * @return 响应结果对象
     */
    public static JsonResult<Void> failed(ResponseCode responseCode, Throwable e) {
        return failed(responseCode, e.getMessage());
    }

    /**
     * 创建响应结果对象，表示"失败"，且封装"失败"的描述
     *
     * @param responseCode "失败"的状态码
     * @param message      "失败"的描述文本
     * @return 响应结果对象
     */
    public static JsonResult<Void> failed(ResponseCode responseCode, String message) {
        JsonResult<Void> jsonResult = new JsonResult<>();
        jsonResult.setState(responseCode.getValue());
        jsonResult.setMessage(message);
        return jsonResult;
    }

}
