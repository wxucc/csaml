package cn.tedu.mall.ams.service.impl;

import cn.tedu.mall.ams.mapper.RoleMapper;
import cn.tedu.mall.ams.mapper.AdminRoleMapper;
import cn.tedu.mall.ams.service.IAdminRoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 管理员角色关联表 服务实现类
 * </p>
 *
 * @author tedu.cn
 * @since 2021-12-02
 */
@Service
@Slf4j
public class AdminRoleServiceImpl implements IAdminRoleService {
    @Autowired
    private AdminRoleMapper adminRoleMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public void addAdminRoles(Long adminId, Long[] roleIds) {
        //删除所有账号角色关联
        adminRoleMapper.deleteAdminRoleByAdminId(adminId);
        for (Long roleId : roleIds) {
            //检查是否有角色
            int exist=roleMapper.selectExistRoleById(roleId);
            if (exist==0){
                log.info("该角色根本不存在："+roleId);
                continue;
            }
            //账号角色关联写入数据库。
            adminRoleMapper.insertAdminRole(adminId,roleId);

        }
    }
}
