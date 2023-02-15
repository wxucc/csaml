package cn.tedu.mall.ams.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * <p> ams_role_permission Mapper 接口</p>
 *
 * @author tedu.cn
 * @since 2021-12-02
 */
public interface RolePermissionMapper{
    int selectExistByPermissionId(@Param("permissionId")Long id);
}
