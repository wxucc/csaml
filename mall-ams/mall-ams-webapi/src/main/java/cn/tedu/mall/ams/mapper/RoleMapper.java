package cn.tedu.mall.ams.mapper;

import cn.tedu.mall.pojo.admin.model.Role;
import cn.tedu.mall.pojo.admin.vo.RoleVO;

import java.util.List;

/**
 * <p> 角色表 Mapper 接口</p>
 *
 * @author tedu.cn
 * @since 2021-12-02
 */
public interface RoleMapper{
    int selectExistRoleById(Long roleId);

    List<RoleVO> selectRoles();

    List<RoleVO> selectRolesLikeName(String query);

    void insertRole(Role role);

    void updateRole(Role role);

    void deleteRol(Long id);
}
