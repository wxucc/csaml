package cn.tedu.mall.sso.mapper.admin;

import cn.tedu.mall.pojo.admin.model.AdminLoginLog;

public interface AdminLoginLogMapper {
    /**
     * 记录登录admin日志
     * @param adminLoginLog
     */
    void insertAdminLoginLog(AdminLoginLog adminLoginLog);
}
