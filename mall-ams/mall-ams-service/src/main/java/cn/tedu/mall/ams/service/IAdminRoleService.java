package cn.tedu.mall.ams.service;

import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 管理员角色关联表 服务类
 * </p>
 *
 * @author tedu.cn
 * @since 2021-12-02
 */
public interface IAdminRoleService{
    @Transactional
    void addAdminRoles(Long adminId, Long[] roleIds);
}
