package cn.tedu.mall.sso.service.impl;

import cn.tedu.mall.common.exception.CoolSharkServiceException;
import cn.tedu.mall.common.pojo.domain.CsmallAuthenticationInfo;
import cn.tedu.mall.common.restful.ResponseCode;
import cn.tedu.mall.common.utils.JwtTokenUtils;
import cn.tedu.mall.pojo.admin.vo.AdminVO;
import cn.tedu.mall.pojo.ums.vo.UserVO;
import cn.tedu.mall.sso.mapper.admin.AdminMapper;
import cn.tedu.mall.sso.mapper.user.UserMapper;
import cn.tedu.mall.sso.pojo.vo.UserInfoVO;
import cn.tedu.mall.sso.service.IUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
@Slf4j
public class UserInfoServiceImpl implements IUserInfoService {
    @Autowired
    private JwtTokenUtils jwtTokenUtils;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private UserMapper userMapper;

    private static final String jwtTokenHead ="Bearer";
    @Override
    public UserInfoVO userInfo(String authToken) {
        String token=authToken.substring(jwtTokenHead.length());
        log.info("获取token:{}",token);
        CsmallAuthenticationInfo userInfo = jwtTokenUtils.getUserInfo(token);
        String type=userInfo.getUserType();
        Long id=userInfo.getId();
        //准备返回数据UserInfoVO
        UserInfoVO userInfoVO=new UserInfoVO();
        if (type!=null&&"ADMIN".equals(type)){
            AdminVO adminVO=adminMapper.findById(id);
            userInfoVO.setUserId(id);
            userInfoVO.setNickname(adminVO.getNickname());
            userInfoVO.setPhone(adminVO.getPhone());
            userInfoVO.setUsername(adminVO.getUsername());
        }else if(type!=null&&"USER".equals(type)){
            UserVO userVO=userMapper.findById(id);
            userInfoVO.setUserId(id);
            userInfoVO.setNickname(userVO.getNickname());
            userInfoVO.setPhone(userVO.getPhone());
            userInfoVO.setUsername(userVO.getUsername());
        }else{
            throw new CoolSharkServiceException(ResponseCode.BAD_REQUEST,"无法获取用户信息");
        }
        return userInfoVO;
    }
}
