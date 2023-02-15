package cn.tedu.mall.sso.security.service.admin;

import cn.tedu.mall.sso.pojo.dto.AdminLoginDTO;

/**
 * <p>管理员单点登录业务接口</p>
 */
public interface IAdminSSOService {

    /**
     * 执行登录
     *
     * @param adminLoginDTO 管理员登录的数据对象
     * @return 管理员登录的Token
     */
    String doLogin(AdminLoginDTO adminLoginDTO);

    void doLogout(String token);
}
