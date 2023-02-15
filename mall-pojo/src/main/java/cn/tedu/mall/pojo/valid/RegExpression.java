package cn.tedu.mall.pojo.valid;

import io.swagger.models.auth.In;

/**
 * <p>正则表达式根级接口，定义了通用的正则表达式和提示文本</p>
 *
 * <p>使用接口主要是为了便于定义常量，并利于相关类实现，以简化在类中使用常量的语法</p>
 */
public interface RegExpression {

    String MESSAGE_SORT = "自定义排序序号必须是0~99的数字";

    String MESSAGE_ENABLE = "选择的是否启用的数据格式错误！";

    String REGEXP_DESCRIPTION = ".{0,255}";
    String MESSAGE_DESCRIPTION = "简介的长度不得超过255字符！";

}
