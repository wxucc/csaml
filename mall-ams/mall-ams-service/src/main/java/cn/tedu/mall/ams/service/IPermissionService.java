package cn.tedu.mall.ams.service;
import cn.tedu.mall.common.restful.JsonPage;
import cn.tedu.mall.pojo.admin.dto.PermissionAddDTO;
import cn.tedu.mall.pojo.admin.dto.PermissionUpdateDTO;
import cn.tedu.mall.pojo.admin.query.PermissionQuery;


/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author tedu.cn
 * @since 2021-12-02
 */
public interface IPermissionService{

    void addPermission(PermissionAddDTO permissionAddDTO);

    JsonPage listPermissions(PermissionQuery permissionQuery);

    void updatePermission(PermissionUpdateDTO permissionUpdateDTO);

    void deletePermission(Long id);
}
