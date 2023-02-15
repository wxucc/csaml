package cn.tedu.mall.ums.controller;


import cn.tedu.mall.common.restful.JsonResult;
import cn.tedu.mall.pojo.ums.dto.UserDetailAddDTO;
import cn.tedu.mall.pojo.ums.dto.UserDetailUpdateDTO;
import cn.tedu.mall.pojo.ums.vo.UserDetailStandardVO;
import cn.tedu.mall.ums.service.IUserDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户详细（不常用）信息表 前端控制器
 * </p>
 *
 * @author tedu.cn
 * @since 2022-02-22
 */
@RestController
@RequestMapping("/ums/userDetail")
@Api(tags="用户详情模块")
public class UserDetailController {
    @Autowired
    private IUserDetailService userDetailService;
    /**
     * 新增用户详情
     */
    @ApiOperation(value="注册后第一次编辑新增用户详情")
    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_user')")
    public JsonResult addUserDetail(UserDetailAddDTO userDetailAddDTO){
        userDetailService.addUserDetail(userDetailAddDTO);
        return JsonResult.ok();
    }
    /**
     * 回显用户详情，包含id值
     */
    @ApiOperation(value="查询当前用户的详情信息")
    @GetMapping("/show")
    @PreAuthorize("hasRole('ROLE_user')")
    public JsonResult<UserDetailStandardVO> getUserDetails(){
        UserDetailStandardVO userDetailStandardVO=userDetailService.getUserDetails();
        return JsonResult.ok(userDetailStandardVO);
    }
    /**
     * 修改用户详情
     */
    @ApiOperation(value="修改用户详情")
    @PostMapping("/modified")
    @PreAuthorize("hasRole('ROLE_user')")
    public JsonResult modifiedUserDetail(UserDetailUpdateDTO userDetailUpdateDTO){
        userDetailService.updateUserDetail(userDetailUpdateDTO);
        return JsonResult.ok();
    }
}

