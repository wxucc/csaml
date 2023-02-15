package cn.tedu.mall.ams.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p> 管理员角色关联表 Mapper 接口</p>
 *
 * @author tedu.cn
 * @since 2021-12-02
 */
public interface AdminRoleMapper {
    void insertAdminRole(@Param("adminId")Long adminId, @Param("roleId")Long roleId);

    int countAdminRoleByAdminidAndRoleid(@Param("adminId")Long adminId, @Param("roleId")Long roleId);

    void deleteAdminRole(@Param("adminId")Long id);


    int selectRelationByRoleid(Long id);

    void deleteAdminRoleByAdminId(@Param("adminId")Long adminId);
}
