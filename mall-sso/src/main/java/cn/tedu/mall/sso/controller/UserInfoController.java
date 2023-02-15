package cn.tedu.mall.sso.controller;

import cn.tedu.mall.common.restful.JsonResult;
import cn.tedu.mall.sso.pojo.vo.UserInfoVO;
import cn.tedu.mall.sso.service.IUserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "用户信息模块")
@RequestMapping("/user/info/sso")
public class UserInfoController {
    @Autowired
    private IUserInfoService userInfoService;
    @ApiOperation(value="获取登录用户信息")
    @GetMapping("/")
    public JsonResult<UserInfoVO> userInfo(@RequestHeader(value="Authorization") String authToken){
        UserInfoVO userInfo=userInfoService.userInfo(authToken);
        return JsonResult.ok(userInfo);
    }
    @Deprecated
    @ApiOperation(value="验证用户认证数据")
    @GetMapping("/auth")
    public Authentication getAuthentication(Authentication authentication){
        return authentication;
    }

}
