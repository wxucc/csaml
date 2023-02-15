package cn.tedu.mall.product.controller;

import cn.tedu.mall.common.restful.JsonPage;
import cn.tedu.mall.common.restful.JsonResult;
import cn.tedu.mall.pojo.product.dto.SkuAddNewDTO;
import cn.tedu.mall.pojo.product.dto.SkuUpdateFullInfoDTO;
import cn.tedu.mall.pojo.product.vo.SkuStandardVO;
import cn.tedu.mall.product.constant.WebConst;
import cn.tedu.mall.product.service.ISkuService;
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
 * <p>SKU（Stock Keeping Unit）控制器</p>
 *
 * @author tedu.cn
 * @since 2021-11-30
 */
@Api(tags = "9. SKU管理模块")
@RestController
@RequestMapping("/pms/sku")
public class SkuController {

    @Autowired
    private ISkuService skuService;

    /**
     * 新增SKU
     */
    @ApiOperationSupport(order = 10)
    @ApiOperation(value = "新增SKU", notes = "需要商品后台【写】权限：/pms/product/update")
    @PreAuthorize("hasAuthority('/pms/product/update')")
    @PostMapping("/addnew")
    public JsonResult<Void> addNew(@Valid SkuAddNewDTO skuAddNewDTO) {
        skuService.addNew(skuAddNewDTO);
        return JsonResult.ok();
    }

    /**
     * 更新SKU
     */
    @ApiOperationSupport(order = 30)
    @ApiOperation(value = "更新SKU", notes = "需要商品后台【写】权限：/pms/product/update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "SKU id", paramType = "path", required = true, dataType = "long")
    })
    @PreAuthorize("hasAuthority('/pms/product/update')")
    @PostMapping("/{id:[0-9]+}/update")
    public JsonResult<Void> updateById(@PathVariable Long id, @Valid SkuUpdateFullInfoDTO skuUpdateFullInfoDTO) {
        skuService.updateFullInfoById(id, skuUpdateFullInfoDTO);
        return JsonResult.ok();
    }

    /**
     * 查询SKU详情
     */
    @ApiOperationSupport(order = 40)
    @ApiOperation(value = "查询SKU详情", notes = "需要商品后台【读】权限：/pms/product/read")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "SKU id", paramType = "path", required = true, dataType = "long")
    })
    @PreAuthorize("hasAuthority('/pms/product/read')")
    @GetMapping("/{id:[0-9]+}")
    public JsonResult<SkuStandardVO> getById(@PathVariable Long id) {
        SkuStandardVO sku = skuService.getById(id);
        return JsonResult.ok(sku);
    }

    /**
     * 根据SPU查询SKU列表
     */
    @ApiOperationSupport(order = 41)
    @ApiOperation(value = "根据SPU查询SKU列表", notes = "需要商品后台【读】权限：/pms/product/read")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "spuId", value = "SPU id", required = true, dataType = "long"),
            @ApiImplicitParam(name = "page", value = "页码", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", dataType = "int")
    })
    @PreAuthorize("hasAuthority('/pms/product/read')")
    @GetMapping("")
    public JsonResult<JsonPage<SkuStandardVO>> list(
            @RequestParam Long spuId,
            @RequestParam(required = false, defaultValue = WebConst.DEFAULT_PAGE) Integer page,
            @RequestParam(required = false, defaultValue = WebConst.DEFAULT_PAGE_SIZE) Integer pageSize) {
        JsonPage<SkuStandardVO> jsonPage = skuService.list(spuId, page, pageSize);
        return JsonResult.ok(jsonPage);
    }

}
