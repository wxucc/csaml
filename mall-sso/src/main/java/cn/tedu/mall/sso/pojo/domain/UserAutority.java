package cn.tedu.mall.sso.pojo.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
@Data
public class UserAutority implements Serializable, GrantedAuthority {
    private String authority;
}
