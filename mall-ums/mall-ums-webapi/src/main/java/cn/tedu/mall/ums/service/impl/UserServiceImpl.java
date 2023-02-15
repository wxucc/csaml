package cn.tedu.mall.ums.service.impl;

import cn.tedu.mall.common.exception.CoolSharkServiceException;
import cn.tedu.mall.common.pojo.domain.CsmallAuthenticationInfo;
import cn.tedu.mall.common.restful.ResponseCode;
import cn.tedu.mall.common.utils.JwtTokenUtils;
import cn.tedu.mall.pojo.ums.dto.ChangePasswordDTO;
import cn.tedu.mall.pojo.ums.dto.UserRegistryDTO;
import cn.tedu.mall.pojo.ums.model.ChangePasswordLog;
import cn.tedu.mall.pojo.ums.model.User;
import cn.tedu.mall.pojo.ums.vo.UserVO;
import cn.tedu.mall.ums.mapper.ChangePasswordLogMapper;
import cn.tedu.mall.ums.mapper.UserMapper;
import cn.tedu.mall.ums.service.IUserService;
import cn.tedu.mall.ums.utils.IdGeneratorUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * 用户基本（常用）信息表 服务实现类
 * </p>
 *
 * @author tedu.cn
 * @since 2022-02-22
 */
@Service
@DubboService
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ChangePasswordLogMapper changePasswordLogMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public UserVO queryOneUser(String username) {

        return userMapper.selectUserByUsername(username);
    }

    @Override
    public void doRegister(UserRegistryDTO userRegistyDTO) {
        //校验一下password是否正确
        validatePassword(userRegistyDTO);
        //转化对象
        User user=new User();
        BeanUtils.copyProperties(userRegistyDTO,user);
        user.setId(IdGeneratorUtils.getDistributeId("user"));
        //加密密码
        user.setPassword(passwordEncoder.encode(userRegistyDTO.getPassword()));
        //写入数据库
        userMapper.insertUser(user);
    }

    @Override
    public void checkValue(String value, String type) {
        //根据传递的参数 检查数据库是否存在数据
        int count=userMapper.selectExistByUsernameOrPhoneOrEmail(value,type);
        if(count>0){
            if(type.equals("username")) {
                throw new CoolSharkServiceException(ResponseCode.CONFLICT, "注册用户名已存在");
            }
            if(type.equals("phone")) {
                throw new CoolSharkServiceException(ResponseCode.CONFLICT, "注册手机号已存在");
            }
            if(type.equals("email")) {
                throw new CoolSharkServiceException(ResponseCode.CONFLICT, "注册邮箱已存在");
            }
        }

    }

    @Override
    public void renewPassword(ChangePasswordDTO changePasswordDTO,String token) {
        //业务层验证
        validateRenewPassword(changePasswordDTO);
        //验证数据库 先拿到userId
        Long userId = getUserId();
        //查询用户信息
        User user=userMapper.selectUserById(userId);
        //对比原密码
        boolean matches = passwordEncoder.matches(changePasswordDTO.getPassword(), user.getPassword());
        if(!matches){
            throw new CoolSharkServiceException(ResponseCode.BAD_REQUEST,"您的原密码不正确,请重新输入");
        }
        //加密密码更新数据库
        String encodedNewPassword=passwordEncoder.encode(changePasswordDTO.getNewPassword());
        userMapper.updatePasswordById(userId,encodedNewPassword);
        //记录日志
        ChangePasswordLog changePasswordLog=new ChangePasswordLog();
        LocalDateTime now = LocalDateTime.now();
        changePasswordLog.setGmtChangePassword(now);
        changePasswordLog.setGmtCreate(now);
        changePasswordLog.setGmtModified(now);
        changePasswordLog.setIp(changePasswordDTO.getIp());
        changePasswordLog.setUserAgent(changePasswordDTO.getUserAgent());
        changePasswordLog.setNewPassword(encodedNewPassword);
        changePasswordLog.setUserId(userId);
        changePasswordLog.setUsername(user.getUsername());
        changePasswordLog.setNickname(user.getNickname());
        changePasswordLogMapper.insertChangePasswordLog(changePasswordLog);
        //清空当前的认证数据,将token放入黑名单,修改密码后重新登录
        String lockedTokenList="token_list_.lock";
        stringRedisTemplate.boundSetOps(lockedTokenList).add(token);
        Long add = stringRedisTemplate.boundSetOps(lockedTokenList).add(token);
    }
    public void validateRenewPassword(ChangePasswordDTO changePasswordDTO){
        if (changePasswordDTO.getPassword()==null){
            throw new CoolSharkServiceException(ResponseCode.BAD_REQUEST,"原密码为空");
        }
        if (changePasswordDTO.getAckNewPassword()==null){
            throw new CoolSharkServiceException(ResponseCode.BAD_REQUEST,"确认新密码为空");
        }
        if (changePasswordDTO.getNewPassword()==null){
            throw new CoolSharkServiceException(ResponseCode.BAD_REQUEST,"新密码为空");
        }
        if (!StringUtils.equals(changePasswordDTO.getNewPassword(),changePasswordDTO.getAckNewPassword())){
            throw new CoolSharkServiceException(ResponseCode.BAD_REQUEST,"两次输入不正确");
        }
    }

    private void validatePassword(UserRegistryDTO userRegistyDTO) {
        String password=userRegistyDTO.getPassword();
        String ackPassword=userRegistyDTO.getAckPassword();
        if (StringUtils.isEmpty(password)||StringUtils.isEmpty(ackPassword)){
            throw new CoolSharkServiceException(ResponseCode.BAD_REQUEST,"密码和确认密码不能为空");
        }
        if (!password.equals(ackPassword)){
            throw new CoolSharkServiceException(ResponseCode.BAD_REQUEST,"密码和确认密码不相等");
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
