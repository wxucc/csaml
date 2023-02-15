package cn.tedu.mall.ams.controller;

import cn.tedu.mall.ams.service.IAdminService;
import cn.tedu.mall.common.restful.JsonPage;
import cn.tedu.mall.common.restful.JsonResult;
import cn.tedu.mall.pojo.admin.dto.AdminAddDTO;
import cn.tedu.mall.pojo.admin.dto.AdminUpdateDTO;
import cn.tedu.mall.pojo.admin.vo.AdminVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 管理员表 前端控制器
 * </p>
 *
 * @author tedu.cn
 * @since 2021-12-02
 */
@RestController
@RequestMapping("/ams/admin")
@Api(tags = "后台用户模块")
@Slf4j
public class AdminController {
    @Autowired
    private IAdminService adminService;
    @ApiOperation(value="新增后台账号")
    @GetMapping("/save")
    @PreAuthorize("hasAuthority('/ams/admin/update')")
    public JsonResult addAdmin(AdminAddDTO adminDTO){
        adminService.addAdmin(adminDTO);
        return JsonResult.ok();
    }
    /**
     * 查询admin列表
     */
    @ApiOperation(value="查询后台用户列表")
    @GetMapping("")
    @ApiImplicitParams({
                    @ApiImplicitParam(value="页数",name="pageNum"),
                    @ApiImplicitParam(value="条数",name="sizeNum")})
    @PreAuthorize("hasAuthority('/ams/admin/read')")
    public JsonResult<JsonPage<AdminVO>> listAdmins(Integer pageNum, Integer sizeNum){
        JsonPage<AdminVO> jsonPage=adminService.listAdmins(pageNum,sizeNum);
        return JsonResult.ok(jsonPage);
    }
    //利用用户名搜索
    @ApiOperation(value="利用账号搜索")
    @GetMapping("/query")
    @ApiImplicitParams({
            @ApiImplicitParam(value="页数",name="pageNum",required = true),
            @ApiImplicitParam(value="条数",name="sizeNum",required = true),
            @ApiImplicitParam(value="查询条件",name="query",required = true)})
    @PreAuthorize("hasAuthority('/ams/admin/read')")
    public JsonResult<JsonPage<AdminVO>> queryAdmins(Integer pageNum, Integer sizeNum,String query){
        JsonPage<AdminVO> jsonPage=adminService.queryAdmins(pageNum,sizeNum,query);
        return JsonResult.ok(jsonPage);
    }
    /**
     * 编辑账号
     */
    @ApiOperation(value="编辑后台用户")
    @PostMapping("/update")
    @PreAuthorize("hasAuthority('/ams/admin/update')")
    public JsonResult updateAdmin(AdminUpdateDTO adminUpdateDTO){
        adminService.updateAdmin(adminUpdateDTO);
        return JsonResult.ok();
    }
    @ApiOperation(value="删除后台用户")
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('/ams/admin/delete')")
    public JsonResult deleteAdmin(Long id){
        adminService.deleteAdmin(id);
        return JsonResult.ok();
    }
}
