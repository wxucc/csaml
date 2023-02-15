package cn.tedu.mall.ams.controller;

import cn.tedu.mall.ams.service.IPermissionService;
import cn.tedu.mall.common.restful.JsonPage;
import cn.tedu.mall.common.restful.JsonResult;
import cn.tedu.mall.pojo.admin.dto.PermissionAddDTO;
import cn.tedu.mall.pojo.admin.dto.PermissionUpdateDTO;
import cn.tedu.mall.pojo.admin.query.PermissionQuery;
import cn.tedu.mall.pojo.admin.vo.PermissionVO;
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
 * 权限表 前端控制器
 * </p>
 *
 * @author tedu.cn
 * @since 2021-12-02
 */
@RestController
@RequestMapping("/ams/permission")
@Api(tags="权限模块")
public class PermissionController {
    @Autowired
    private IPermissionService permissionService;
    /**
     * 新增权限
     */
    @ApiOperation(value="新增权限")
    @PreAuthorize("hasAuthority('/ams/admin/update')")
    @PostMapping("/save")
    public JsonResult addPermission(PermissionAddDTO permissionAddDTO){
            permissionService.addPermission(permissionAddDTO);
            return JsonResult.ok();
    }
    /**
     * 权限查询搜索
     */
    @ApiOperation(value="后台权限搜索列表")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('/ams/admin/read')")
    public JsonResult<JsonPage<PermissionVO>> listPermissions(PermissionQuery permissionQuery){
        JsonPage jsonPage=permissionService.listPermissions(permissionQuery);
        return JsonResult.ok(jsonPage);
    }
    /**
     *编辑权限，但是不可轻易动路径
     */
    @ApiOperation(value="编辑权限",notes="保留接口，但是权限不可轻易编辑")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('/ams/admin/update')")
    public JsonResult updatePermission(PermissionUpdateDTO permissionUpdateDTO){
        permissionService.updatePermission(permissionUpdateDTO);
        return JsonResult.ok();
    }
    /**
     *删除权限
     */
    @ApiOperation(value="删除权限",notes="保留接口，但是权限不可轻易编辑")
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('/ams/admin/update')")
    public JsonResult deletePermission(Long id){
        permissionService.deletePermission(id);
        return JsonResult.ok();
    }
}
