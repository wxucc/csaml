package cn.tedu.mall.sso.service;

import cn.tedu.mall.sso.pojo.vo.UserInfoVO;

public interface IUserInfoService {
    UserInfoVO userInfo(String authToken);
}
