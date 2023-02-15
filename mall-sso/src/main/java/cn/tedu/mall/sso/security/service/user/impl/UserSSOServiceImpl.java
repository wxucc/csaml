package cn.tedu.mall.sso.security.service.user.impl;

import cn.tedu.mall.common.exception.CoolSharkServiceException;
import cn.tedu.mall.common.pojo.domain.CsmallAuthenticationInfo;
import cn.tedu.mall.common.restful.ResponseCode;
import cn.tedu.mall.common.utils.JwtTokenUtils;
import cn.tedu.mall.pojo.ums.model.UserLoginLog;
import cn.tedu.mall.sso.mapper.user.UserLoginLogMapper;
import cn.tedu.mall.sso.pojo.dto.UserLoginDTO;
import cn.tedu.mall.sso.pojo.domain.UserUserDetails;
import cn.tedu.mall.sso.security.service.user.IUserSSOService;
import cn.tedu.mall.sso.security.service.user.UserSSOUserDetailsService;
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

@Service
@Slf4j
public class UserSSOServiceImpl implements IUserSSOService {
    @Autowired
    private UserSSOUserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtils jwtTokenUtils;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private UserLoginLogMapper userLoginLogMapper;
    @Override
    public String doLogin(UserLoginDTO userLoginDTO){
        String token;
        UserUserDetails userDetails = (UserUserDetails) userDetailsService.loadUserByUsername(userLoginDTO.getUsername());
        if (userDetails == null) {
            throw new CoolSharkServiceException(ResponseCode.BAD_REQUEST, "登录失败！用户名密码错误");
        }
        if (!passwordEncoder.matches(userLoginDTO.getPassword(), userDetails.getPassword())) {
            throw new CoolSharkServiceException(ResponseCode.BAD_REQUEST, "登录失败！用户名密码错误");
        }
        CsmallAuthenticationInfo userInfo = generateFromUser(userDetails);
        token = jwtTokenUtils.generateToken(userInfo);
        //记录登录时间
        UserLoginLog userLoginLog=new UserLoginLog();
        userLoginLog.setUserId(userDetails.getId());
        LocalDateTime now=LocalDateTime.now();
        userLoginLog.setGmtCreate(now);
        userLoginLog.setGmtModified(now);
        userLoginLog.setGmtLogin(now);
        userLoginLog.setIp(userLoginDTO.getIp());
        userLoginLog.setUserAgent(userLoginDTO.getUserAgent());
        userLoginLog.setUsername(userDetails.getUsername());
        userLoginLog.setNickname(userDetails.getNickname());
        userLoginLogMapper.insertUserLoginLog(userLoginLog);
        return token;
    }
    public CsmallAuthenticationInfo generateFromUser(UserUserDetails userDetails){
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) userDetails.getAuthorities();
        List<String> authorityValues=new ArrayList<>();
        for (GrantedAuthority authority : authorities) {
            String value=authority.getAuthority();
            authorityValues.add(value);
        }
        CsmallAuthenticationInfo userInfo=new CsmallAuthenticationInfo();
        userInfo.setId(userDetails.getId());
        userInfo.setUsername(userDetails.getUsername());
        userInfo.setUserType("USER");
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
            stringRedisTemplate.boundSetOps(lockedTokenList).add(authToken);
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
