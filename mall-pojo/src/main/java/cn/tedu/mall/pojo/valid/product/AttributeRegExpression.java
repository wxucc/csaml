package cn.tedu.mall.pojo.valid.product;

import cn.tedu.mall.pojo.valid.RegExpression;

/**
 * <p>属性数据的表达式接口，定义了相关的正则表达式和提示文本</p>
 *
 * <p>使用接口主要是为了便于定义常量，并利于相关类实现，以简化在类中使用常量的语法</p>
 */
public interface AttributeRegExpression extends RegExpression {

    String REGEXP_NAME = ".{2,6}";
    String MESSAGE_NAME = "名称必须由2~16字符组成！";

    String REGEXP_VALUE_LIST = ".{0,255}";
    String MESSAGE_VALUE_LIST = "备选值列表的长度不得超过255字符！";

    String REGEXP_UNIT = ".{0,6}";
    String MESSAGE_UNIT = "计量单位的长度不得超过6字符！";

}
