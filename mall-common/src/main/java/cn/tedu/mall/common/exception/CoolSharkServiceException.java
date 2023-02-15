package cn.tedu.mall.common.exception;

import cn.tedu.mall.common.restful.ResponseCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 业务异常
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CoolSharkServiceException extends RuntimeException {

    private ResponseCode responseCode;

    public CoolSharkServiceException(ResponseCode responseCode, String message) {
        super(message);
        setResponseCode(responseCode);
    }

}
