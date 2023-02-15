package cn.tedu.mall.product.controller;

import cn.tedu.mall.common.restful.JsonPage;
import cn.tedu.mall.common.restful.JsonResult;
import cn.tedu.mall.pojo.product.dto.SpuAddNewDTO;
import cn.tedu.mall.pojo.product.dto.SpuUpdateDTO;
import cn.tedu.mall.pojo.product.query.SpuQuery;
import cn.tedu.mall.pojo.product.vo.AlbumStandardVO;
import cn.tedu.mall.pojo.product.vo.SpuListItemVO;
import cn.tedu.mall.pojo.product.vo.SpuStandardVO;
import cn.tedu.mall.product.constant.WebConst;
import cn.tedu.mall.product.service.ISpuService;
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
 * <p>SPU（Standard Product Unit）控制器</p>
 *
 * @author tedu.cn
 * @since 2021-11-30
 */
@Api(tags = "8. SPU管理模块")
@RestController
@RequestMapping("/pms/spu")
public class SpuController {

    @Autowired
    private ISpuService spuService;

    /**
     * 新增SPU
     */
    @ApiOperationSupport(order = 10)
    @ApiOperation(value = "新增SPU", notes = "需要商品后台【写】权限：/pms/product/update")
    @PreAuthorize("hasAuthority('/pms/product/update')")
    @PostMapping("/addnew")
    public JsonResult<Void> addNew(@Valid SpuAddNewDTO spuAddnewDTO) {
        spuService.addNew(spuAddnewDTO);
        return JsonResult.ok();
    }

    /**
     * 更新SPU
     */
    @ApiOperationSupport(order = 30)
    @ApiOperation(value = "更新SPU", notes = "需要商品后台【写】权限：/pms/product/update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "SPU id", paramType = "path",required = true, dataType = "long")
    })
    @PreAuthorize("hasAuthority('/pms/product/update')")
    @PostMapping("/{id:[0-9]+}/update")
    public JsonResult<Void> updateById(@PathVariable("id") Long id, @Valid SpuUpdateDTO spuDTO) {
        spuService.updateById(id, spuDTO);
        return JsonResult.ok();
    }

    /**
     * 通过审核SPU
     */
    @ApiOperationSupport(order = 31)
    @ApiOperation(value = "通过审核SPU", notes = "需要商品后台【写】权限：/pms/product/update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "SPU id", paramType = "path",required = true, dataType = "long")
    })
    @PreAuthorize("hasAuthority('/pms/product/update')")
    @PostMapping("/{id:[0-9]+}/checked-status/pass")
    public JsonResult<Void> passCheck(@PathVariable("id") Long id) {
        spuService.passCheck(id);
        return JsonResult.ok();
    }

    /**
     * 同步SPU库存值（用于增减SKU或SKU的变化变化后SPU价格未更新的情况）
     */
    @ApiOperationSupport(order = 32)
    @ApiOperation(value = "同步SPU库存值", notes = "用于增减SKU或SKU的库存变化后SPU库存未更新的情况，需要商品后台【写】权限：/pms/product/update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "SPU id", paramType = "path",required = true, dataType = "long")
    })
    @PreAuthorize("hasAuthority('/pms/product/update')")
    @PostMapping("/{id:[0-9]+}/stock/synchronise")
    public JsonResult<Void> synchroniseStock(@PathVariable("id") Long id) {
        spuService.synchroniseStock(id);
        return JsonResult.ok();
    }

    /**
     * 同步SPU价格（用于增减SKU或SKU的变化变化后SPU价格未更新的情况）
     */
    @ApiOperationSupport(order = 33)
    @ApiOperation(value = "同步SPU价格", notes = "用于增减SKU或SKU的变化变化后SPU价格未更新的情况，需要商品后台【写】权限：/pms/product/update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "SPU id", paramType = "path", required = true,dataType = "long")
    })
    @PreAuthorize("hasAuthority('/pms/product/update')")
    @PostMapping("/{id:[0-9]+}/price/synchronise")
    public JsonResult<Void> synchronisePrice(@PathVariable("id") Long id) {
        spuService.synchronisePrice(id);
        return JsonResult.ok();
    }

    /**
     * 查询SPU详情
     */
    @ApiOperationSupport(order = 40)
    @ApiOperation(value = "查询SPU详情", notes = "需要商品后台【读】权限：/pms/product/read")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "相册id", required = true, dataType = "long")
    })
    @PreAuthorize("hasAuthority('/pms/product/read')")
    @GetMapping("/{id:[0-9]+}")
    public JsonResult<SpuStandardVO> getById(@PathVariable Long id) {
        return JsonResult.ok(spuService.getById(id));
    }

    /**
     * 查询SPU列表
     */
    @ApiOperationSupport(order = 41)
    @ApiOperation(value = "查询SPU列表", notes = "需要商品后台【读】权限：/pms/product/read")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", dataType = "int")
    })
    @PreAuthorize("hasAuthority('/pms/product/read')")
    @GetMapping("")
    public JsonResult<JsonPage<SpuListItemVO>> list(
            @RequestParam(required = false, defaultValue = WebConst.DEFAULT_PAGE) Integer page,
            @RequestParam(required = false, defaultValue = WebConst.DEFAULT_PAGE_SIZE) Integer pageSize) {
        JsonPage<SpuListItemVO> spuList = spuService.list(page, pageSize);
        return JsonResult.ok(spuList);
    }

    /**
     * 自定义条件查询SPU列表
     */
    @ApiOperationSupport(order = 42)
    @ApiOperation(value = "自定义条件查询SPU列表", notes = "条件根据非空动态变化，需要商品后台【读】权限：/pms/product/read")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", dataType = "int")
    })
    @PreAuthorize("hasAuthority('/pms/product/read')")
    @GetMapping("/by-custom-condition")
    public JsonResult<JsonPage<SpuListItemVO>> listByCustomCondition(
            SpuQuery spuQuery,
            @RequestParam(required = false, defaultValue = WebConst.DEFAULT_PAGE) Integer page,
            @RequestParam(required = false, defaultValue = WebConst.DEFAULT_PAGE_SIZE) Integer pageSize) {
        JsonPage<SpuListItemVO> spuList = spuService.listFromDB(spuQuery, page, pageSize);
        return JsonResult.ok(spuList);
    }

}
