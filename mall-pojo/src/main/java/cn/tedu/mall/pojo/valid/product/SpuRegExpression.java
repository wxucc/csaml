package cn.tedu.mall.pojo.valid.product;

import cn.tedu.mall.pojo.valid.RegExpression;

/**
 * <p>SPU数据的表达式接口，定义了相关的正则表达式和提示文本</p>
 *
 * <p>使用接口主要是为了便于定义常量，并利于相关类实现，以简化在类中使用常量的语法</p>
 */
public interface SpuRegExpression extends RegExpression {

    String REGEXP_NAME = ".{2,16}";
    String MESSAGE_NAME = "名称必须由2~16字符组成！";

    String REGEXP_TITLE = ".{2,16}";
    String MESSAGE_TITLE = "标题名称必须由2~16字符组成！";

    String REGEXP_TYPE_NUMBER = ".{0,16}";
    String MESSAGE_TYPE_NUMBER = "SPU编号的长度不得超过16字符！";

    String REGEXP_UNIT = ".{1,6}";
    String MESSAGE_UNIT = "计件单位的长度不得超过6字符！";

    String REGEXP_KEYWORDS = ".{0,255}";
    String MESSAGE_KEYWORDS = "关键词列表的长度不得超过255字符！";

    String REGEXP_PICTURES = ".{0,500}";
    String MESSAGE_PICTURES = "组图URL数据的长度不得超过500字符！";

    String REGEXP_TAGS = ".{0,255}";
    String MESSAGE_TAGS = "关键词列表的长度不得超过255字符！";

}
