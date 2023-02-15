package cn.tedu.mall.ums.service;

import cn.tedu.mall.pojo.ums.dto.ChangePasswordDTO;
import cn.tedu.mall.pojo.ums.dto.UserRegistryDTO;
import cn.tedu.mall.pojo.ums.vo.UserVO;

/**
 * <p>
 * 用户基本（常用）信息表 服务类
 * </p>
 *
 * @author tedu.cn
 * @since 2022-02-22
 */
public interface IUserService{

    UserVO queryOneUser(String username);

    void doRegister(UserRegistryDTO userRegistyDTO);

    void checkValue(String value, String type);

    void renewPassword(ChangePasswordDTO changePasswordDTO,String token);
}
