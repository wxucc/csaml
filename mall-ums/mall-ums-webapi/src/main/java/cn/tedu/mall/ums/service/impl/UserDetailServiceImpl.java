package cn.tedu.mall.ums.service.impl;

import cn.tedu.mall.common.exception.CoolSharkServiceException;
import cn.tedu.mall.common.pojo.domain.CsmallAuthenticationInfo;
import cn.tedu.mall.common.restful.ResponseCode;
import cn.tedu.mall.pojo.ums.dto.UserDetailAddDTO;
import cn.tedu.mall.pojo.ums.dto.UserDetailUpdateDTO;
import cn.tedu.mall.pojo.ums.model.UserDetail;
import cn.tedu.mall.pojo.ums.vo.UserDetailStandardVO;
import cn.tedu.mall.ums.mapper.UserDetailMapper;
import cn.tedu.mall.ums.service.IUserDetailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户详细（不常用）信息表 服务实现类
 * </p>
 *
 * @author tedu.cn
 * @since 2022-02-22
 */
@Service
public class UserDetailServiceImpl implements IUserDetailService {
    @Autowired
    private UserDetailMapper userDetailMapper;
    @Override
    public void addUserDetail(UserDetailAddDTO userDetailAddDTO) {
        Long userId = getUserId();
        UserDetail userDetail=new UserDetail();
        //查询已存在

        UserDetailStandardVO userDetailStandardVO = userDetailMapper.selectUserDetailByUserId(userId);
        if(userDetailStandardVO==null){
            //转化对象
            BeanUtils.copyProperties(userDetailAddDTO,userDetail);
            userDetail.setUserId(userId);
            userDetailMapper.insertUserDetail(userDetail);
        }else{
            throw new CoolSharkServiceException(ResponseCode.BAD_REQUEST,"用户详情已经存在请进行修改操作");
        }


    }

    @Override
    public UserDetailStandardVO getUserDetails() {
        Long userId=getUserId();
        UserDetailStandardVO userDetailStandardVO=userDetailMapper.selectUserDetailByUserId(userId);
        return userDetailStandardVO;
    }

    @Override
    public void updateUserDetail(UserDetailUpdateDTO userDetailUpdateDTO) {
        Long userId = getUserId();
        UserDetail userDetail=new UserDetail();
        //查询已存在
        UserDetailStandardVO userDetailStandardVO = userDetailMapper.selectUserDetailById(userDetailUpdateDTO.getId());
        if(userDetailStandardVO!=null){
            Long userId1 = userDetailStandardVO.getUserId();
            if(userId!=userId1){
                throw new CoolSharkServiceException(ResponseCode.BAD_REQUEST,"您修改的用户详情不属于您");
            }
            BeanUtils.copyProperties(userDetailUpdateDTO,userDetail);
            userDetailMapper.updateUserDetailById(userDetail);
        }else{
            throw new CoolSharkServiceException(ResponseCode.BAD_REQUEST,"用户详情不存在，请新增用户详情");
        }
    }

    //TODO 可以和购物车业务层方法合并简化
    public CsmallAuthenticationInfo getUserInfo(){
        //从security环境获取username,先拿到authentication
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        //如果不是空的可以调用dubbo远程微服务获取adminVO
        if(authentication!=null){
            CsmallAuthenticationInfo csmallAuthenticationInfo=(CsmallAuthenticationInfo)authentication.getCredentials();
            return csmallAuthenticationInfo;
        }else{
            throw new CoolSharkServiceException(ResponseCode.UNAUTHORIZED,"没有登录者信息");
        }
    }
    public Long getUserId(){
        CsmallAuthenticationInfo userInfo = getUserInfo();
        return userInfo.getId();
    }
}
