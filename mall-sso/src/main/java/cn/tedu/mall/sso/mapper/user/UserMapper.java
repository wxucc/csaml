package cn.tedu.mall.sso.mapper.user;

import cn.tedu.mall.pojo.ums.model.User;
import cn.tedu.mall.pojo.ums.vo.UserVO;
import org.springframework.beans.factory.annotation.Qualifier;

@Qualifier("db2SqlSessionTemplate")
public interface UserMapper {
    User findByUsername(String username);

    UserVO findById(Long id);
}
