package cn.tedu.mall.ams.controller;

import cn.tedu.mall.ams.service.IAdminRoleService;
import cn.tedu.mall.common.restful.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 管理员角色关联表 前端控制器
 * </p>
 *
 * @author tedu.cn
 * @since 2021-12-02
 */
@RestController
@RequestMapping("/ams/adminRole")
@Api(tags = "账号角色关联模块")
public class AdminRoleController {
    @Autowired
    private IAdminRoleService adminRoleService;
    /**
     * 账号新增关联角色
     */
    @ApiOperation(value="重新绑定角色账号关联")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('/ams/admin/read')")
    @ApiImplicitParams({
            @ApiImplicitParam(value="用户id",name="adminId"),
            @ApiImplicitParam(value="角色ids,数组",name="roleIds",allowMultiple = true,dataType = "Array")
    })
    public JsonResult addAdminRole(Long adminId, Long[] roleIds){
        adminRoleService.addAdminRoles(adminId,roleIds);
        return JsonResult.ok();
    }
}
