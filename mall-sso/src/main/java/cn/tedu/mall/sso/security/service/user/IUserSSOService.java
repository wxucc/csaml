package cn.tedu.mall.sso.security.service.user;

import cn.tedu.mall.common.exception.CoolSharkServiceException;
import cn.tedu.mall.sso.pojo.dto.UserLoginDTO;


public interface IUserSSOService {
    String doLogin(UserLoginDTO userLoginDTO) throws CoolSharkServiceException;

    void doLogout(String token) throws CoolSharkServiceException;
}
