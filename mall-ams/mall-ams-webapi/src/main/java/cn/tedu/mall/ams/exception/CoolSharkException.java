package cn.tedu.mall.ams.exception;

public class CoolSharkException extends RuntimeException{
    private Integer code;
    private String message;
    public CoolSharkException(String message, Integer code){
        this.message=message;
        this.code=code;
    }
    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    @Override
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
