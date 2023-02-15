package cn.tedu.mall.sso.security.service.admin;

import cn.tedu.mall.pojo.admin.model.Admin;
import cn.tedu.mall.sso.mapper.admin.AdminMapper;
import cn.tedu.mall.sso.mapper.admin.PermissionMapper;
import cn.tedu.mall.common.exception.CoolSharkServiceException;
import cn.tedu.mall.sso.pojo.domain.AdminAutority;
import cn.tedu.mall.sso.pojo.domain.AdminUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>2021-12-14</p>
 * <ul>
 *     <li>新增此类，将代码从原Config中抽取出来</li>
 *     <li>修改错误提示文本</li>
 *     <li>增加对isEnable的判断</li>
 * </ul>
 * @return
 */
@Service
@Slf4j
public class AdminSSOUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 获取与用户名匹配的管理员信息
        Admin admin = null;
        try {
            admin = adminMapper.findByUsername(username);
        } catch (CoolSharkServiceException e) {
            throw new BadCredentialsException("登录失败，用户名不正确！");
        }
        if(admin==null){
            return null;
        }
        // 检查管理员是否启用
        if (admin.getEnable() != 1) {
            throw new BadCredentialsException("登录失败，用户账号已经被禁用！");
        }
        // 获取该管理员的权限列表
        List<String> permissions = permissionMapper.findPermissionsByAdminId(admin.getId());
        // 封装权限信息
        List<AdminAutority> authorities = new ArrayList<>();
        for (String permission : permissions) {
            AdminAutority authority=new AdminAutority();
            authority.setValue(permission);
            authorities.add(authority);
        }
        AdminUserDetails userDetails=new AdminUserDetails();
        BeanUtils.copyProperties(admin,userDetails);
        userDetails.setAuthorities(authorities);
        // 返回
        return userDetails;
    }

}
