package cn.tedu.mall.ums.service;

import cn.tedu.mall.pojo.ums.dto.UserDetailAddDTO;
import cn.tedu.mall.pojo.ums.dto.UserDetailUpdateDTO;
import cn.tedu.mall.pojo.ums.vo.UserDetailStandardVO;

/**
 * <p>
 * 用户详细（不常用）信息表 服务类
 * </p>
 *
 * @author tedu.cn
 * @since 2022-02-22
 */
public interface IUserDetailService{

    void addUserDetail(UserDetailAddDTO userDetailAddDTO);

    UserDetailStandardVO getUserDetails();

    void updateUserDetail(UserDetailUpdateDTO userDetailUpdateDTO);
}
