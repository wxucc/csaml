package cn.tedu.mall.pojo.product.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>属性模板</p>
 *
 * @author tedu.cn
 * @since 2021-11-30
 */
@Data
public class AttributeTemplate implements Serializable {

    /**
     * 记录id
     */
    private Long id;

    /**
     * 属性模板名称
     */
    private String name;

    /**
     * 属性模板名称的拼音
     */
    private String pinyin;

    /**
     * 关键词列表，各关键词使用英文的逗号分隔
     */
    private String keywords;

    /**
     * 自定义排序序号
     */
    private Integer sort;

    /**
     * 数据创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 数据最后修改时间
     */
    private LocalDateTime gmtModified;

}