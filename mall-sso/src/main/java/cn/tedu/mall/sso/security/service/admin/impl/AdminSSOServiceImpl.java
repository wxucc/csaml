package cn.tedu.mall.sso.security.service.admin.impl;

import cn.tedu.mall.common.pojo.domain.CsmallAuthenticationInfo;
import cn.tedu.mall.common.utils.JwtTokenUtils;
import cn.tedu.mall.pojo.admin.model.AdminLoginLog;
import cn.tedu.mall.sso.mapper.admin.AdminLoginLogMapper;
import cn.tedu.mall.sso.pojo.dto.AdminLoginDTO;
import cn.tedu.mall.sso.pojo.domain.AdminUserDetails;
import cn.tedu.mall.sso.security.service.admin.AdminSSOUserDetailsService;
import cn.tedu.mall.sso.security.service.admin.IAdminSSOService;
import cn.tedu.mall.common.exception.CoolSharkServiceException;
import cn.tedu.mall.common.restful.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>管理员单点登录业务</p>
 */
@Service
@Slf4j
public class AdminSSOServiceImpl implements IAdminSSOService {

    @Autowired
    private AdminSSOUserDetailsService userDetailsService;
    @Autowired
    private AdminLoginLogMapper adminLoginLogMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtils jwtTokenUtils;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public String doLogin(AdminLoginDTO adminLoginDTO){
        //生成返回的token
        String token;
        AdminUserDetails userDetails = (AdminUserDetails) userDetailsService.loadUserByUsername(adminLoginDTO.getUsername());
        if (userDetails == null) {
            throw new CoolSharkServiceException(ResponseCode.BAD_REQUEST, "登录失败！用户名密码错误");
        }
        if (!passwordEncoder.matches(adminLoginDTO.getPassword(), userDetails.getPassword())) {
            throw new CoolSharkServiceException(ResponseCode.BAD_REQUEST, "登录失败！用户名密码错误");
        }
        CsmallAuthenticationInfo csmallAuthenticationInfo = generateFromAdmin(userDetails);
        token = jwtTokenUtils.generateToken(csmallAuthenticationInfo);
        //记录登录日志
        AdminLoginLog adminLoginLog=new AdminLoginLog();
        adminLoginLog.setAdminId(userDetails.getId());
        LocalDateTime now=LocalDateTime.now();
        adminLoginLog.setGmtCreate(now);
        adminLoginLog.setGmtModified(now);
        adminLoginLog.setGmtLogin(now);
        adminLoginLog.setIp(adminLoginDTO.getIp());
        adminLoginLog.setUserAgent(adminLoginDTO.getUserAgent());
        adminLoginLog.setUsername(userDetails.getUsername());
        adminLoginLog.setNickname(userDetails.getNickname());
        adminLoginLogMapper.insertAdminLoginLog(adminLoginLog);
        return token;
    }
    public CsmallAuthenticationInfo generateFromAdmin(AdminUserDetails userDetails){
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) userDetails.getAuthorities();
        List<String> authorityValues=new ArrayList<>();
        for (GrantedAuthority authority : authorities) {
            String value=authority.getAuthority();
            authorityValues.add(value);
        }
        CsmallAuthenticationInfo userInfo=new CsmallAuthenticationInfo();
        userInfo.setId(userDetails.getId());
        userInfo.setUsername(userDetails.getUsername());
        userInfo.setUserType("ADMIN");
        userInfo.setAuthorities(authorityValues);
        return userInfo;

    }
    @Value("${jwt.tokenHead}")
    private String tokenHead;//Bearer
    @Override
    public void doLogout(String token){
        if (token != null && token.startsWith(tokenHead)) {
            //拿到jwt token
            String authToken = token.substring(tokenHead.length());
            //写入redis 锁住 这里采用list分日期存储,方便后续定时清理
            String lockedTokenList="token_list_.lock";
            Long add = stringRedisTemplate.boundSetOps(lockedTokenList).add(authToken);
            if(add==0){
                throw new CoolSharkServiceException(ResponseCode.CONFLICT,"当前用户已经登出,不必重复登出");
            }
        }else{
            //header不存在token
            throw new CoolSharkServiceException(ResponseCode.NOT_FOUND,"当前客户端没有登录状态");
        }
    }
}
