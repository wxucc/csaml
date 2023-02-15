package cn.tedu.mall.pojo.valid.product;

import cn.tedu.mall.pojo.valid.RegExpression;

/**
 * <p>SKU数据的表达式接口，定义了相关的正则表达式和提示文本</p>
 *
 * <p>使用接口主要是为了便于定义常量，并利于相关类实现，以简化在类中使用常量的语法</p>
 */
public interface PictureRegExpression extends RegExpression {

    String REGEXP_URL = ".{0,255}";
    String MESSAGE_URL = "URL的长度不得超过255字符！";

}
