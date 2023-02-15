package cn.tedu.mall.product.controller;

import cn.tedu.mall.common.restful.JsonPage;
import cn.tedu.mall.common.restful.JsonResult;
import cn.tedu.mall.pojo.product.dto.AttributeAddNewDTO;
import cn.tedu.mall.pojo.product.dto.AttributeUpdateDTO;
import cn.tedu.mall.pojo.product.vo.AttributeDetailsVO;
import cn.tedu.mall.pojo.product.vo.AttributeStandardVO;
import cn.tedu.mall.product.constant.WebConst;
import cn.tedu.mall.product.service.IAttributeService;
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
 * <p>属性控制器</p>
 *
 * @author tedu.cn
 * @since 2021-11-30
 */
@Api(tags = "6. 属性管理模块")
@RestController
@RequestMapping(value = "/pms/attributes", produces = "application/json;charset=utf-8")
public class AttributeController {

    @Autowired
    private IAttributeService attributeService;

    /**
     * 增加属性
     */
    @ApiOperationSupport(order = 10)
    @ApiOperation(value = "增加属性", notes = "需要商品后台【写】权限：/pms/product/update")
    @PreAuthorize("hasAuthority('/pms/product/update')")
    @PostMapping("/addnew")
    public JsonResult<Void> addNew(@Valid AttributeAddNewDTO attributeAddnewDTO) {
        attributeService.addNew(attributeAddnewDTO);
        return JsonResult.ok();
    }

    /**
     * 删除属性
     */
    @ApiOperationSupport(order = 20)
    @ApiOperation(value = "删除属性", notes = "需要商品后台【写】权限：/pms/product/update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "属性id", paramType = "path", required = true, dataType = "long")
    })
    @PreAuthorize("hasAuthority('/pms/product/update')")
    @PostMapping("/{id:[0-9]+}/delete")
    public JsonResult<Void> deleteById(@PathVariable Long id) {
        attributeService.deleteById(id);
        return JsonResult.ok();
    }

    /**
     * 更新属性
     */
    @ApiOperationSupport(order = 30)
    @ApiOperation(value = "更新属性", notes = "需要商品后台【写】权限：/pms/product/update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "属性id", paramType = "path", required = true, dataType = "long")
    })
    @PreAuthorize("hasAuthority('/pms/product/update')")
    @PostMapping("/{id:[0-9]+}/update")
    public JsonResult<Void> updateById(@PathVariable Long id, @Valid AttributeUpdateDTO attributeUpdateDTO) {
        attributeService.updateById(id, attributeUpdateDTO);
        return JsonResult.ok();
    }

    /**
     * 查询属性详情（含所属模板详情）
     */
    @ApiOperationSupport(order = 40)
    @ApiOperation(value = "查询属性详情（含所属模板详情）", notes = "需要商品后台【读】权限：/pms/product/read")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "属性id", paramType = "path", required = true, dataType = "long")
    })
    @PreAuthorize("hasAuthority('/pms/product/read')")
    @GetMapping("/{id:[0-9]+}/details")
    public JsonResult<AttributeDetailsVO> getDetailsById(@PathVariable Long id) {
        AttributeDetailsVO attributeDetailsVO = attributeService.getDetailsById(id);
        return JsonResult.ok(attributeDetailsVO);
    }

    /**
     * 根据模板查询属性列表
     */
    @ApiOperationSupport(order = 41)
    @ApiOperation(value = "根据模板查询属性列表", notes = "需要商品后台【读】权限：/pms/product/read")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "templateId", value = "模板id", required = true, dataType = "long"),
            @ApiImplicitParam(name = "page", value = "页码", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", dataType = "int")
    })
    @PreAuthorize("hasAuthority('/pms/product/read')")
    @GetMapping("")
    public JsonResult<JsonPage<AttributeStandardVO>> listByTemplateId(
            @RequestParam Long templateId,
            @RequestParam(required = false, defaultValue = WebConst.DEFAULT_PAGE) Integer page,
            @RequestParam(required = false, defaultValue = WebConst.DEFAULT_PAGE_SIZE) Integer pageSize) {
        JsonPage<AttributeStandardVO> attributes = attributeService.listByTemplateId(templateId, page, pageSize);
        return JsonResult.ok(attributes);
    }

    /**
     * 根据模板查询销售属性列表
     */
    @ApiOperationSupport(order = 42)
    @ApiOperation(value = "根据模板查询销售属性列表", notes = "需要商品后台【读】权限：/pms/product/read")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "templateId", value = "模板id", required = true, dataType = "long"),
            @ApiImplicitParam(name = "page", value = "页码", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", dataType = "int")
    })
    @PreAuthorize("hasAuthority('/pms/product/read')")
    @GetMapping("/sale")
    public JsonResult<JsonPage<AttributeStandardVO>> listSaleAttributesByTemplateId(
            @RequestParam Long templateId,
            @RequestParam(required = false, defaultValue = WebConst.DEFAULT_PAGE) Integer page,
            @RequestParam(required = false, defaultValue = WebConst.DEFAULT_PAGE_SIZE) Integer pageSize) {
        JsonPage<AttributeStandardVO> attributes = attributeService.listSaleAttributesByTemplateId(templateId, page, pageSize);
        return JsonResult.ok(attributes);
    }

    /**
     * 根据模板查询非销售属性列表
     */
    @ApiOperationSupport(order = 43)
    @ApiOperation(value = "根据模板查询非销售属性列表", notes = "需要商品后台【读】权限：/pms/product/read")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "templateId", value = "模板id", required = true, dataType = "long"),
            @ApiImplicitParam(name = "page", value = "页码", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", dataType = "int")
    })
    @PreAuthorize("hasAuthority('/pms/product/read')")
    @GetMapping("/non-sale")
    public JsonResult<JsonPage<AttributeStandardVO>> listNonSaleAttributesByTemplateId(
            @RequestParam Long templateId,
            @RequestParam(required = false, defaultValue = WebConst.DEFAULT_PAGE) Integer page,
            @RequestParam(required = false, defaultValue = WebConst.DEFAULT_PAGE_SIZE) Integer pageSize) {
        JsonPage<AttributeStandardVO> attributes = attributeService.listNonSaleAttributesByTemplateId(templateId, page, pageSize);
        return JsonResult.ok(attributes);
    }

}
