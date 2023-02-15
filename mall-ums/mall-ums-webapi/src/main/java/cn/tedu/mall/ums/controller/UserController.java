package cn.tedu.mall.ums.controller;


import cn.tedu.mall.common.exception.CoolSharkServiceException;
import cn.tedu.mall.common.restful.JsonResult;
import cn.tedu.mall.common.restful.ResponseCode;
import cn.tedu.mall.common.utils.JwtTokenUtils;
import cn.tedu.mall.pojo.ums.dto.ChangePasswordDTO;
import cn.tedu.mall.pojo.ums.dto.UserRegistryDTO;
import cn.tedu.mall.ums.service.IUserService;
import cn.tedu.mall.ums.utils.LoginUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * <p>
 * 用户基本（常用）信息表 前端控制器
 * </p>
 *
 * @author tedu.cn
 * @since 2022-02-22
 */
@RestController
@RequestMapping("/ums/user")
@Api(tags = "用户信息功能")
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private JwtTokenUtils jwtTokenUtils;
    /**
     * 注册用户
     * @param userRegistyDTO
     * @return
     */
    @ApiOperation(value="注册用户")
    @PostMapping("/register")
    @PreAuthorize("permitAll()")
    public JsonResult doRegister(UserRegistryDTO userRegistyDTO){
        userService.doRegister(userRegistyDTO);
        return JsonResult.ok();
    }

    @ApiOperation(value="校验手机,邮箱,用户名是否重复")
    @PostMapping("/checkValue")
    @PreAuthorize("permitAll()")
    @ApiImplicitParams({
            @ApiImplicitParam(value="校验值",name="value",type = "string"),
            @ApiImplicitParam(value="校验值类型,phone,username,email",name="type",type = "string")
    })
    public JsonResult checkValue(String value,String type){
        userService.checkValue(value,type);
        return JsonResult.ok();
    }
    @Value("${jwt.tokenHead}")
    private String tokenHead;//Bearer
    /**
     * 修改登录密码
     */
    @ApiOperation(value="修改登录密码")
    @PostMapping("/renew/password")
    @PreAuthorize("hasRole('ROLE_user')")
    public JsonResult renewPassword(@Valid ChangePasswordDTO changePasswordDTO, HttpServletRequest request,@RequestHeader("Authorization") String authToken){
        String ip = LoginUtils.getIpAddress(request);
        changePasswordDTO.setIp(ip);
        String userAgent = request.getHeader("User-Agent");
        changePasswordDTO.setUserAgent(userAgent);
        String token = getToken(authToken);
        userService.renewPassword(changePasswordDTO,token);
        return JsonResult.ok();
    }
    public String getToken(String authToken){
        if (authToken==null||!(authToken.startsWith("Bearer "))){
            throw new CoolSharkServiceException(ResponseCode.BAD_REQUEST,"无法从请求中拿到token");
        }
        return authToken.substring(tokenHead.length());
    }
}
