package cn.tedu.mall.ams.mapper;

import cn.tedu.mall.pojo.admin.model.Permission;
import cn.tedu.mall.pojo.admin.query.PermissionQuery;
import cn.tedu.mall.pojo.admin.vo.PermissionVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p> 权限表 Mapper 接口</p>
 *
 * @author tedu.cn
 * @since 2021-12-02
 */
public interface PermissionMapper{
    List<Permission> selectPermissionsByAdminId(@Param("adminId")Long id);

    void insertPermission(Permission permission);

    List<PermissionVO> selectPermissions(PermissionQuery permissionQuery);

    void updatePermission(Permission permission);

    void deletePermission(@Param("id")Long id);
}
