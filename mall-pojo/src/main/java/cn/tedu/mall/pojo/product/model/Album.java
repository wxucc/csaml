package cn.tedu.mall.pojo.product.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>相册</p>
 *
 * @author tedu.cn
 * @since 2021-11-30
 */
@Data
public class Album implements Serializable {

    /**
     * 记录id
     */
    private Long id;

    /**
     * 相册名称
     */
    private String name;

    /**
     * 相册简介
     */
    private String description;

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