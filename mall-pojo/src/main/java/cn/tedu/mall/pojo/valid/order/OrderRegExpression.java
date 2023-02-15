package cn.tedu.mall.pojo.valid.order;

import cn.tedu.mall.common.validation.RegExpressions;

public interface OrderRegExpression extends RegExpressions {
    String REGEXP_CONTACT_NAME = ".{2,4}";
    String MESSAGE_CONTACT_NAME = "名称必须由2~4字符组成！";

    String REGEXP_MOBILE_PHONE = "^1(?:3\\d|4[4-9]|5[0-35-9]|6[67]|7[013-8]|8\\d|9\\d)\\d{8}$";
    String MESSAGE_MOBILE_PHONE = "请输入中国大陆有效手机号";

    String REGEXP_TELEPHONE = "0\\d{2,3}-\\d{7,8}|\\(?0\\d{2,3}[)-]?\\d{7,8}|\\(?0\\d{2,3}[)-]*\\d{7,8}";
    String MESSAGE_TELEPHONE = "请输入正确座机号码";
}
