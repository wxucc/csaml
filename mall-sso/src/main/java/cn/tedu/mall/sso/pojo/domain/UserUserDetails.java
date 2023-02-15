package cn.tedu.mall.sso.pojo.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
public class UserUserDetails implements Serializable, UserDetails {
    private Long id;
    private String username;
    private String password;
    private String nickname;
    private String avatar;
    private String phone;
    private String email;
    private Integer isEnable;
    private Integer rewardPoint;
    private String lastLoginIp;
    private Integer loginCount;
    private Date gmtLastLogin;
    private Date gmtCreate;
    private Date gmtModified;
    private List<UserAutority> authorities;

    public UserUserDetails() {
        authorities =new ArrayList<>();
        UserAutority authority=new UserAutority();
        authority.setAuthority("ROLE_user");
        authorities.add(authority);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
