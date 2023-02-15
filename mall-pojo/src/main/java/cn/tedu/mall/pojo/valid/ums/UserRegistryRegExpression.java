package cn.tedu.mall.pojo.valid.ums;

import cn.tedu.mall.common.validation.RegExpressions;

public interface UserRegistryRegExpression extends RegExpressions {
    String REGEXP_USER_NICKNAME = ".{2,16}";
    String MESSAGE_USER_NICKNAME = "名称必须由2~16字符组成！";

    String REGEXP_USER_PHONE = "^1[34589][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]$";
    String MESSAGE_USER_PHONE = "请填写正确的手机号！";

    String REGEXP_USER_EMAIL = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
    String MESSAGE_USER_EMAIL = "请填写正确格式的邮箱！";
}
