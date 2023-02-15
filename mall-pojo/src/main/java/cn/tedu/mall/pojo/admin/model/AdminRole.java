package cn.tedu.mall.pojo.admin.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 管理员角色关联表
 * </p>
 *
 * @author tedu.cn
 * @since 2021-12-02
 */
@Data
public class AdminRole implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 管理员id
     */
    private Long adminId;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 数据创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 数据最后修改时间
     */
    private LocalDateTime gmtModified;


}
