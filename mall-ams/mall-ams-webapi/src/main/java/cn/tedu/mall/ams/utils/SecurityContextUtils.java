package cn.tedu.mall.ams.utils;

import cn.tedu.mall.common.pojo.domain.CsmallAuthenticationInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 使用Security Context获取User信息
 */
@Slf4j
public class SecurityContextUtils {

    public static CsmallAuthenticationInfo getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = null;
        if (authentication instanceof UsernamePasswordAuthenticationToken) {
            usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) authentication;
        } else {
            log.info("当前登录认证状态不存在");
            return null;
        }
        CsmallAuthenticationInfo userInfo = (CsmallAuthenticationInfo) usernamePasswordAuthenticationToken.getCredentials();
        return userInfo;
    }

    public static Long getUserId() {
        CsmallAuthenticationInfo userInfo = getUserInfo();
        return userInfo.getId();
    }
    public static String getUsername(){
        CsmallAuthenticationInfo userInfo = getUserInfo();
        return userInfo.getUsername();
    }
    public static String getUserType(){
        CsmallAuthenticationInfo userInfo = getUserInfo();
        return userInfo.getUserType();
    }

}
