package cn.tedu.mall.product.controller;

import cn.tedu.mall.common.restful.JsonPage;
import cn.tedu.mall.common.restful.JsonResult;
import cn.tedu.mall.pojo.product.dto.CategoryAddNewDTO;
import cn.tedu.mall.pojo.product.dto.CategoryUpdateBaseInfoDTO;
import cn.tedu.mall.pojo.product.dto.CategoryUpdateFullInfoDTO;
import cn.tedu.mall.pojo.product.vo.CategoryAddNewResultVO;
import cn.tedu.mall.pojo.product.vo.CategoryStandardVO;
import cn.tedu.mall.product.constant.WebConst;
import cn.tedu.mall.product.service.ICategoryService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>类别控制器</p>
 *
 * @author tedu.cn
 * @since 2021-11-30
 */
@Api(tags = "1. 类别管理模块")
@RestController
@RequestMapping("/pms/categories")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    /**
     * 新增类别
     */
    @ApiOperationSupport(order = 10)
    @ApiOperation(value = "新增类别", notes = "需要商品后台【写】权限：/pms/product/update")
    @PreAuthorize("hasAuthority('/pms/product/update')")
    @PostMapping("/addnew")
    public JsonResult<CategoryAddNewResultVO> addNew(@Valid CategoryAddNewDTO categoryAddNewDTO) {
        Long id = categoryService.addNew(categoryAddNewDTO);
        return JsonResult.ok(new CategoryAddNewResultVO(id));
    }

    /**
     * 删除类别
     */
    @ApiOperationSupport(order = 20)
    @ApiOperation(value = "删除类别", notes = "需要商品后台【写】权限：/pms/product/update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "类别id", paramType = "path", required = true, dataType = "long" )
    })
    @PreAuthorize("hasAuthority('/pms/product/update')")
    @PostMapping("/{id:[0-9]+}/delete")
    public JsonResult<Void> deleteById(@PathVariable Long id) {
        categoryService.deleteById(id);
        return JsonResult.ok();
    }

    /**
     * 启用类别
     */
    @ApiOperationSupport(order = 30)
    @ApiOperation(value = "启用类别", notes = "需要商品后台【写】权限：/pms/product/update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "类别id", paramType = "path", required = true, dataType = "long")
    })
    @PreAuthorize("hasAuthority('/pms/product/update')")
    @PostMapping("/{id:[0-9]+}/status/enable")
    public JsonResult<Void> setEnableById(@PathVariable Long id) {
        categoryService.setEnableById(id);
        return JsonResult.ok();
    }

    /**
     * 禁用类别
     */
    @ApiOperationSupport(order = 31)
    @ApiOperation(value = "禁用类别", notes = "需要商品后台【写】权限：/pms/product/update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "类别id", paramType = "path", required = true, dataType = "long")
    })
    @PreAuthorize("hasAuthority('/pms/product/update')")
    @PostMapping("/{id:[0-9]+}/status/disable")
    public JsonResult<Void> setDisableById(@PathVariable Long id) {
        categoryService.setDisableById(id);
        return JsonResult.ok();
    }

    /**
     * 显示类别
     */
    @ApiOperationSupport(order = 32)
    @ApiOperation(value = "显示类别", notes = "需要商品后台【写】权限：/pms/product/update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "类别id", paramType = "path", required = true, dataType = "long")
    })
    @PreAuthorize("hasAuthority('/pms/product/update')")
    @PostMapping("/{id:[0-9]+}/status/show")
    public JsonResult<Void> setDisplayById(@PathVariable Long id) {
        categoryService.setDisplayById(id);
        return JsonResult.ok();
    }

    /**
     * 隐藏（不显示）类别
     */
    @ApiOperationSupport(order = 33)
    @ApiOperation(value = "隐藏（不显示）类别", notes = "需要商品后台【写】权限：/pms/product/update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "类别id", paramType = "path", required = true, dataType = "long")
    })
    @PreAuthorize("hasAuthority('/pms/product/update')")
    @PostMapping("/{id:[0-9]+}/status/hide")
    public JsonResult<Void> setHiddenById(@PathVariable Long id) {
        categoryService.setHiddenById(id);
        return JsonResult.ok();
    }

    /**
     * 更新类别的基本信息
     */
    @ApiOperationSupport(order = 38)
    @ApiOperation(value = "更新类别的基本信息", notes = "需要商品后台【写】权限：/pms/product/update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "类别id", paramType = "path", required = true, dataType = "long")
    })
    @PreAuthorize("hasAuthority('/pms/product/update')")
    @PostMapping("/{id:[0-9]+}/base-info/update")
    public JsonResult<Void> updateById(@PathVariable Long id, @Valid CategoryUpdateBaseInfoDTO categoryUpdateBaseInfoDTO) {
        categoryService.updateBaseInfoById(id, categoryUpdateBaseInfoDTO);
        return JsonResult.ok();
    }

    /**
     * 更新类别的全部信息
     */
    @ApiOperationSupport(order = 39)
    @ApiOperation(value = "更新类别的全部信息", notes = "需要商品后台【写】权限：/pms/product/update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "类别id", paramType = "path", required = true, dataType = "long")
    })
    @PreAuthorize("hasAuthority('/pms/product/update')")
    @PostMapping("/{id:[0-9]+}/full-info/update")
    public JsonResult<Void> updateById(@PathVariable Long id, @Valid CategoryUpdateFullInfoDTO categoryUpdateFullInfoDTO) {
        categoryService.updateFullInfoById(id, categoryUpdateFullInfoDTO);
        return JsonResult.ok();
    }

    /**
     * 查询类别详情
     */
    @ApiOperationSupport(order = 40)
    @ApiOperation(value = "查询类别详情", notes = "需要商品后台【读】权限：/pms/product/read")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "类别id", paramType = "path", required = true, dataType = "long")
    })
    @PreAuthorize("hasAuthority('/pms/product/read')")
    @GetMapping("/{id:[0-9]+}")
    public JsonResult<CategoryStandardVO> getById(@PathVariable Long id) {
        return JsonResult.ok(categoryService.getById(id));
    }

    /**
     * 根据父级类别查询子级类别列表
     */
    @ApiOperationSupport(order = 41)
    @ApiOperation(value = "根据父级类别查询子级类别列表", notes = "需要商品后台【读】权限：/pms/product/read")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "parentId", value = "父级类别id", required = true, dataType = "long"),
            @ApiImplicitParam(name = "page", value = "页码", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", dataType = "int")
    })
    @PreAuthorize("hasAuthority('/pms/product/read')")
    @GetMapping("/list-by-parent")
    public JsonResult<JsonPage<CategoryStandardVO>> listByParent(
            @RequestParam Long parentId,
            @RequestParam(required = false, defaultValue = WebConst.DEFAULT_PAGE) Integer page,
            @RequestParam(required = false, defaultValue = WebConst.DEFAULT_PAGE_SIZE) Integer pageSize) {
        JsonPage<CategoryStandardVO> categories = categoryService.listByParent(parentId, page, pageSize);
        return JsonResult.ok(categories);
    }

    /**
     * 根据品牌查询类别列表
     */
    @ApiOperationSupport(order = 42)
    @ApiOperation(value = "根据品牌查询类别列表", notes = "需要商品后台【读】权限：/pms/product/read")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "brandId", value = "品牌id", required = true, dataType = "long"),
            @ApiImplicitParam(name = "page", value = "页码", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", dataType = "int")
    })
    @PreAuthorize("hasAuthority('/pms/product/read')")
    @GetMapping("/list-by-brand")
    public JsonResult<JsonPage<CategoryStandardVO>> listByBrand(
            @RequestParam Long brandId,
            @RequestParam(required = false, defaultValue = WebConst.DEFAULT_PAGE) Integer page,
            @RequestParam(required = false, defaultValue = WebConst.DEFAULT_PAGE_SIZE) Integer pageSize) {
        JsonPage<CategoryStandardVO> categories = categoryService.listByBrand(brandId, page, pageSize);
        return JsonResult.ok(categories);
    }

}
