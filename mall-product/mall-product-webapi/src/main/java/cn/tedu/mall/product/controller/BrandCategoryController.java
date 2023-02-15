package cn.tedu.mall.product.controller;

import cn.tedu.mall.common.restful.JsonResult;
import cn.tedu.mall.product.service.IBrandCategoryService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>品牌分类关联控制器</p>
 *
 * @author tedu.cn
 * @since 2021-11-30
 */
@Api(tags = "3. 品牌分类关联管理模块")
@RestController
@RequestMapping("/pms/brand-category-relations")
public class BrandCategoryController {

    @Autowired
    private IBrandCategoryService brandCategoryService;

    /**
     * 绑定品牌与类别
     */
    @ApiOperationSupport(order = 10)
    @ApiOperation(value = "绑定品牌与类别", notes = "需要商品后台【写】权限：/pms/product/update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "brandId", value = "品牌id", required = true, dataType = "long"),
            @ApiImplicitParam(name = "categoryId", value = "类别id", required = true, dataType = "long")
    })
    @PreAuthorize("hasAuthority('/pms/product/update')")
    @PostMapping("/bind")
    public JsonResult<Void> bindBrandAndCategory(@RequestParam Long brandId, @RequestParam Long categoryId) {
        brandCategoryService.bindBrandAndCategory(brandId, categoryId);
        return JsonResult.ok();
    }

    /**
     * 解绑品牌与类别
     */
    @ApiOperationSupport(order = 20)
    @ApiOperation(value = "解绑品牌与类别", notes = "需要商品后台【写】权限：/pms/product/update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "brandId", value = "品牌id", required = true, dataType = "long"),
            @ApiImplicitParam(name = "categoryId", value = "类别id", required = true, dataType = "long")
    })
    @PreAuthorize("hasAuthority('/pms/product/update')")
    @PostMapping("/unbind")
    public JsonResult<Void> unbindBrandAndCategory(@RequestParam Long brandId, @RequestParam Long categoryId) {
        brandCategoryService.unbindBrandAndCategory(brandId, categoryId);
        return JsonResult.ok();
    }

}
