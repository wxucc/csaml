package cn.tedu.mall.ums.mapper;

import cn.tedu.mall.pojo.ums.model.ChangePasswordLog;

/**
 * <p> 用户修改密码日志表 Mapper 接口</p>
 *
 * @author tedu.cn
 * @since 2022-02-22
 */
public interface ChangePasswordLogMapper{
    void insertChangePasswordLog(ChangePasswordLog changePasswordLog);
}
