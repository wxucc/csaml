package cn.tedu.mall.pojo.product.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>类别</p>
 *
 * @author tedu.cn
 * @since 2021-11-30
 */
@Data
public class Category implements Serializable {

    /**
     * 记录id
     */
    private Long id;

    /**
     * 类别名称
     */
    private String name;

    /**
     * 父级类别id，如果无父级，则为0
     */
    private Long parentId;

    /**
     * 深度，最顶级类别的深度为1，次级为2，以此类推
     */
    private Integer depth;

    /**
     * 关键词列表，各关键词使用英文的逗号分隔
     */
    private String keywords;

    /**
     * 自定义排序序号
     */
    private Integer sort;

    /**
     * 图标图片的URL
     */
    private String icon;

    /**
     * 是否启用，1=启用，0=未启用
     */
    private Integer enable;

    /**
     * 是否为父级（是否包含子级），1=是父级，0=不是父级
     */
    private Integer parent;

    /**
     * 是否显示在导航栏中，1=启用，0=未启用
     */
    private Integer display;

    /**
     * 数据创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 数据最后修改时间
     */
    private LocalDateTime gmtModified;

}