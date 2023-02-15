package cn.tedu.mall.pojo.admin.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author tedu.cn
 * @since 2021-12-02
 */
@Data

public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 名称
     */

    private String name;

    /**
     * 值
     */

    private String value;

    /**
     * 描述
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
