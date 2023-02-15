package cn.tedu.mall.common.validation;

/**
 * <p>正则表达式</p>
 */
public interface RegExpressions {

    String REGEXP_ADMIN_USERNAME = "^[a-zA-Z]{1}[0-9a-zA-Z]{3,15}$";
    String MESSAGE_ADMIN_USERNAME = "用户名必须是由字母、数字组成的4~16字符，且第1个字符必须是字母！";

    String REGEXP_ADMIN_PASSWORD = "^[\\u0020-\\u007e]{4,16}$";
    String MESSAGE_ADMIN_PASSWORD = "密码的长度必须是4~16位！";

    String REGEXP_USER_USERNAME = "^[a-zA-Z]{1}[0-9a-zA-Z]{3,15}$";
    String MESSAGE_USER_USERNAME = "用户名必须是由字母、数字组成的4~16字符，且第1个字符必须是字母！";

    String REGEXP_USER_PASSWORD = "^[\\u0020-\\u007e]{4,16}$";
    String MESSAGE_USER_PASSWORD = "密码的长度必须是4~16位！";
}
