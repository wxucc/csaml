package cn.tedu.mall.sso.utils;

import cn.tedu.mall.common.exception.CoolSharkServiceException;
import cn.tedu.mall.common.restful.ResponseCode;
import cn.tedu.mall.sso.pojo.domain.AdminUserDetails;
import cn.tedu.mall.sso.pojo.domain.UserUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 使用securitycontext获取user信息
 */
@Slf4j
public class SecurityContextUtils {
    public static UserDetails getUserInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=null;
        if(authentication instanceof UsernamePasswordAuthenticationToken){
            usernamePasswordAuthenticationToken=(UsernamePasswordAuthenticationToken)authentication;
        }else{
            return null;
        }
        Object credentials = usernamePasswordAuthenticationToken.getCredentials();
        if (credentials instanceof AdminUserDetails){
            log.info("当前认证对象的用户数据是admin:{}",credentials);
            return (AdminUserDetails)credentials;
        }else if(credentials instanceof UserUserDetails){
            log.info("user:{}",credentials);
            return (UserUserDetails)credentials;
        }else{
            log.info("当前认证对象的用户数据是:{}",credentials);
            return (User)credentials;
        }
    }
    public static Long getUserId(){
        UserDetails userInfo = getUserInfo();
        if (userInfo==null){
            return 0l;
        }
        if (userInfo instanceof AdminUserDetails){
            log.info("当前认证对象的用户数据是admin:{}",userInfo);
            return ((AdminUserDetails)userInfo).getId();
        }else if(userInfo instanceof UserUserDetails){
            log.info("user:{}",userInfo);
            return ((UserUserDetails)userInfo).getId();
        }else{
            log.info("当前认证对象的用户数据是:{}",userInfo);
            throw new CoolSharkServiceException(ResponseCode.CONFLICT,"登录被用户不存在id");
        }
    }
}
