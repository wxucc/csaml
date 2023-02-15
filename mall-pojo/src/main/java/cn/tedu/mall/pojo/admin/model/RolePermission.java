package cn.tedu.mall.pojo.admin.model;


import lombok.Data;


import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * ams_role_permission
 * </p>
 *
 * @author tedu.cn
 * @since 2021-12-02
 */
@Data

public class RolePermission implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long id;

    /**
     * 角色id
     */

    private Long roleId;

    /**
     * 权限id
     */

    private Long permissionId;

    /**
     * 数据创建时间
     */

    private LocalDateTime gmtCreate;

    /**
     * 数据最后修改时间
     */

    private LocalDateTime gmtModified;


}
