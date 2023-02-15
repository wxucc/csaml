package cn.tedu.mall.sso.controller;

import cn.tedu.mall.sso.pojo.dto.AdminLoginDTO;
import cn.tedu.mall.sso.security.service.admin.IAdminSSOService;
import cn.tedu.mall.common.restful.JsonResult;

import cn.tedu.mall.sso.pojo.vo.TokenVO;
import cn.tedu.mall.sso.utils.LoginUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * <p>管理员单点登录控制器</p>
 */
@RestController
@RequestMapping("/admin/sso")
@Api(tags = "后台管理用户认证")
@Slf4j
public class AdminSSOController {
    /**
     * 测试代码
     * @param authentication
     * @return
     */
    @Deprecated
    @GetMapping("/home")
    @ApiOperation(value="测试用户数据认证对象生成问题")
    private JsonResult home(Authentication authentication){
        return JsonResult.ok(authentication);
    }
    @Autowired
    private IAdminSSOService adminSSOService;
    @Value("${jwt.tokenHead}")
    private String jwtTokenHead;

    /**
     * <p>登录login</p>
     * <p>主要就是验证是否登录账号密码正确</p>
     */
    @ApiOperation(value = "后台单点登录认证登录")
    @PostMapping("/login")
    public JsonResult<TokenVO> doLogin(@Valid AdminLoginDTO adminLoginDTO, HttpServletRequest request){
        //先补充数据
        String remoteAddr = LoginUtils.getIpAddress(request);//如果是localhost访问会记录ipv6格式的本机地址,正常
        log.info("远程ip地址:{}",remoteAddr);
        String userAgent=request.getHeader("User-Agent");
        log.info("远程客户端:{}",userAgent);
        adminLoginDTO.setIp(remoteAddr);
        adminLoginDTO.setUserAgent(userAgent);
        String token = adminSSOService.doLogin(adminLoginDTO);
        TokenVO tokenVO = new TokenVO();
        tokenVO.setTokenHeader(jwtTokenHead);
        tokenVO.setTokenValue(token);
        return JsonResult.ok(tokenVO);
    }

    /**
     * <p>登出logout</p>
     * <p>没有任何实际业务逻辑</p>
     */
    @ApiOperation(value = "单点登录认证登出")
    @PostMapping("/logout")
    public JsonResult doLogout(@RequestHeader(name = "Authorization") String token){
        adminSSOService.doLogout(token);
        return JsonResult.ok();
    }

}
