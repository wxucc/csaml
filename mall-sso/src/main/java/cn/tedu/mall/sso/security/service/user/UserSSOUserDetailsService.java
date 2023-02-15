package cn.tedu.mall.sso.security.service.user;

import cn.tedu.mall.common.exception.CoolSharkServiceException;
import cn.tedu.mall.pojo.ums.model.User;
import cn.tedu.mall.sso.mapper.user.UserMapper;
import cn.tedu.mall.sso.pojo.domain.UserUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserSSOUserDetailsService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 获取与用户名匹配的管理员信息
        User user = null;
        try {
             user = userMapper.findByUsername(username);
        } catch (CoolSharkServiceException e) {
            throw new BadCredentialsException("登录失败，用户名不正确！");
        }
        if(user==null){
            return null;
        }
        // 检查管理员是否启用
        if (user.getEnable() != 1) {
            throw new BadCredentialsException("登录失败，用户账号已经被禁用！");
        }
        UserUserDetails userDetails=new UserUserDetails();
        BeanUtils.copyProperties(user,userDetails);
        // 返回
        return userDetails;
    }
}
