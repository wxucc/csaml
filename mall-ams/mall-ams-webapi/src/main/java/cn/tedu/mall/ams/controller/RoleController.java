package cn.tedu.mall.ams.controller;

import cn.tedu.mall.ams.service.IRoleService;
import cn.tedu.mall.common.restful.JsonPage;
import cn.tedu.mall.common.restful.JsonResult;
import cn.tedu.mall.pojo.admin.dto.RoleAddDTO;
import cn.tedu.mall.pojo.admin.dto.RoleUpdateDTO;
import cn.tedu.mall.pojo.admin.vo.RoleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author tedu.cn
 * @since 2021-12-02
 */
@RestController
@RequestMapping("/ams/role")
@Api(tags="角色模块")
public class RoleController {
    @Autowired
    private IRoleService roleService;
    /**
     * 角色列表
     */
    @ApiOperation(value="后台查看角色列表")
    @GetMapping("/list")
    @ApiImplicitParams({
            @ApiImplicitParam(value="页数",name="pageNum",required = true),
            @ApiImplicitParam(value="条数",name="sizeNum",required = true)
    })
    @PreAuthorize("hasAuthority('/ams/admin/read')")
    public JsonResult<JsonPage<RoleVO>> listRoles(Integer pageNum, Integer sizeNum){
            JsonPage<RoleVO> roles=roleService.listRoles(pageNum,sizeNum);
            return JsonResult.ok(roles);
    }
    /**
     * 根据角色名称模糊查询角色
     */
    @ApiOperation(value="根据角色名称模糊查询角色列表")
    @GetMapping("/query")
    @ApiImplicitParams({
            @ApiImplicitParam(value="页数",name="pageNum",required = true),
            @ApiImplicitParam(value="条数",name="sizeNum",required = true),
            @ApiImplicitParam(value="角色名称",name="query",required = true)
    })
    @PreAuthorize("hasAuthority('/ams/admin/read')")
    public JsonResult<JsonPage<RoleVO>> queryRoles(Integer pageNum, Integer sizeNum,String query){
        JsonPage<RoleVO> roles=roleService.queryRoles(pageNum,sizeNum,query);
        return JsonResult.ok(roles);
    }
    /**
     * 新增角色
     */
    @ApiOperation(value="新增角色")
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('/ams/admin/update')")
    public JsonResult addRole(RoleAddDTO roleAddDTO){
        roleService.addRole(roleAddDTO);
        return JsonResult.ok();
    }
    /**
     * 更新角色
     */
    @ApiOperation(value="更新角色")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('/ams/admin/update')")
    public JsonResult addRole(RoleUpdateDTO roleUpdateDTO){
        roleService.updateRole(roleUpdateDTO);
        return JsonResult.ok();
    }
    /**
     * 删除角色
     */
    @ApiOperation(value="删除角色",notes = "只能删除无关联角色")
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('/ams/admin/delete')")
    public JsonResult deleteRole(Long id){
        roleService.deleteRole(id);
        return JsonResult.ok();
    }
}
