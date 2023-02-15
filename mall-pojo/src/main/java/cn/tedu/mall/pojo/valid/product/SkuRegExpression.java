package cn.tedu.mall.pojo.valid.product;

import cn.tedu.mall.pojo.valid.RegExpression;

/**
 * <p>SKU数据的表达式接口，定义了相关的正则表达式和提示文本</p>
 *
 * <p>使用接口主要是为了便于定义常量，并利于相关类实现，以简化在类中使用常量的语法</p>
 */
public interface SkuRegExpression extends RegExpression {

    String REGEXP_TITLE = ".{2,16}";
    String MESSAGE_TITLE = "标题名称必须由2~16字符组成！";

    String REGEXP_BAR_CODE = ".{16}";
    String MESSAGE_BAR_CODE = "条型码的长度不得超过16字符！（临时设定）";

    String REGEXP_PICTURES = ".{0,500}";
    String MESSAGE_PICTURES = "条型码的长度不得超过500字符！（临时设定）";

}
