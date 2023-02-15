package cn.tedu.mall.product.controller;

import cn.tedu.mall.common.restful.JsonPage;
import cn.tedu.mall.common.restful.JsonResult;
import cn.tedu.mall.pojo.product.dto.BrandAddNewDTO;
import cn.tedu.mall.pojo.product.dto.BrandUpdateDTO;
import cn.tedu.mall.pojo.product.vo.BrandStandardVO;
import cn.tedu.mall.product.constant.WebConst;
import cn.tedu.mall.product.service.IBrandService;
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
 * <p>品牌控制器</p>
 *
 * @author tedu.cn
 * @since 2021-11-30
 */
@Api(tags = "2. 品牌管理模块")
@RestController
@RequestMapping("/pms/brands")
public class BrandController {

    @Autowired
    private IBrandService brandService;

    /**
     * 增加品牌
     */
    @ApiOperationSupport(order = 10)
    @ApiOperation(value = "增加品牌", notes = "需要商品后台【写】权限：/pms/product/update")
    @PreAuthorize("hasAuthority('/pms/product/update')")
    @PostMapping("/addnew")
    public JsonResult<Void> addNew(@Valid BrandAddNewDTO brandAddnewDTO) {
        brandService.addNew(brandAddnewDTO);
        return JsonResult.ok();
    }

    /**
     * 删除品牌
     */
    @ApiOperationSupport(order = 20)
    @ApiOperation(value = "删除品牌", notes = "查询关联，删除无关联数据，需要商品后台【写】权限：/pms/product/update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "品牌id", paramType = "path", required = true, dataType = "long")
    })
    @PreAuthorize("hasAuthority('/pms/product/update')")
    @PostMapping("/{id:[0-9]+}/delete")
    public JsonResult<Void> deleteById(@PathVariable Long id) {
        brandService.deleteById(id);
        return JsonResult.ok();
    }

    /**
     * 更新品牌
     */
    @ApiOperationSupport(order = 30)
    @ApiOperation(value = "更新品牌", notes = "需要商品后台【写】权限：/pms/product/update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "品牌id", paramType = "path", required = true, dataType = "long")
    })
    @PreAuthorize("hasAuthority('/pms/product/update')")
    @PostMapping("/{id:[0-9]+}/update")
    public JsonResult<Void> updateById(@PathVariable Long id, @Valid BrandUpdateDTO brandUpdateDTODTO) {
        brandService.updateFullInfoById(id, brandUpdateDTODTO);
        return JsonResult.ok();
    }

    /**
     * 查询品牌详情
     */
    @ApiOperationSupport(order = 40)
    @ApiOperation(value = "查询品牌详情", notes = "需要商品后台【读】权限：/pms/product/read")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "品牌id", paramType = "path", required = true, dataType = "long")
    })
    @PreAuthorize("hasAuthority('/pms/product/read')")
    @GetMapping("/{id:[0-9]+}")
    public JsonResult<BrandStandardVO> getById(@PathVariable Long id) {
        return JsonResult.ok(brandService.getById(id));
    }

    /**
     * 查询品牌列表
     */
    @ApiOperationSupport(order = 41)
    @ApiOperation(value = "查询品牌列表", notes = "需要商品后台【读】权限：/pms/product/read")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", dataType = "int")
    })
    @PreAuthorize("hasAuthority('/pms/product/read')")
    @GetMapping("")
    public JsonResult<JsonPage<BrandStandardVO>> list(
            @RequestParam(required = false, defaultValue = WebConst.DEFAULT_PAGE) Integer page,
            @RequestParam(required = false, defaultValue = WebConst.DEFAULT_PAGE_SIZE) Integer pageSize) {
        return JsonResult.ok(brandService.list(page, pageSize));
    }

    /**
     * 根据类别查询品牌列表
     */
    @ApiOperationSupport(order = 42)
    @ApiOperation(value = "根据类别查询品牌列表", notes = "需要商品后台【读】权限：/pms/product/read")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "categoryId", value = "类别id", required = true, dataType = "long"),
            @ApiImplicitParam(name = "page", value = "页码", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", dataType = "int")
    })
    @PreAuthorize("hasAuthority('/pms/product/read')")
    @GetMapping("/by-category")
    public JsonResult<JsonPage<BrandStandardVO>> listByCategoryId(
            @RequestParam Long categoryId,
            @RequestParam(required = false, defaultValue = WebConst.DEFAULT_PAGE) Integer page,
            @RequestParam(required = false, defaultValue = WebConst.DEFAULT_PAGE_SIZE) Integer pageSize) {
        return JsonResult.ok(brandService.listByCategoryId(categoryId, page, pageSize));
    }

}
