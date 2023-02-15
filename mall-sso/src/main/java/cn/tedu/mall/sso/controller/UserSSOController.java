package cn.tedu.mall.sso.controller;

import cn.tedu.mall.common.restful.JsonResult;
import cn.tedu.mall.sso.pojo.dto.UserLoginDTO;
import cn.tedu.mall.sso.security.service.user.IUserSSOService;
import cn.tedu.mall.sso.pojo.vo.TokenVO;
import cn.tedu.mall.sso.utils.LoginUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 实现前台用户登录的请求和登出请求
 */
@RestController
@RequestMapping("/user/sso")
@Api(tags = "前台管理用户认证")
@Slf4j
public class UserSSOController {
    @Autowired
    private IUserSSOService userSSOService;
    @Value("${jwt.tokenHead}")
    private String jwtTokenHead;
    @ApiOperation(value = "前台单点登录认证登录")
    @PostMapping("/login")
    public JsonResult<TokenVO> doLogin(@Valid UserLoginDTO userLoginDTO, HttpServletRequest request){
        //先补充数据
        String ip = LoginUtils.getIpAddress(request);
        log.info("远程ip地址:{}",ip);
        String userAgent=request.getHeader("User-Agent");
        log.info("远程客户端:{}",userAgent);
        userLoginDTO.setIp(ip);
        userLoginDTO.setUserAgent(userAgent);
        String token = userSSOService.doLogin(userLoginDTO);
        TokenVO tokenVO = new TokenVO();
        tokenVO.setTokenHeader(jwtTokenHead);
        tokenVO.setTokenValue(token);
        return JsonResult.ok(tokenVO);
    }
    /**
     * <p>登出logout</p>
     * <p>没有任何实际业务逻辑</p>
     */
    @ApiOperation(value = "前台单点登录认证登出")
    @PostMapping("/logout")
    public JsonResult doLogout(@RequestHeader(name = "Authorization") String token){
        userSSOService.doLogout(token);
        return JsonResult.ok();
    }
}
