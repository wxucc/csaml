package cn.tedu.mall.product.controller;

import cn.tedu.mall.common.restful.JsonPage;
import cn.tedu.mall.common.restful.JsonResult;
import cn.tedu.mall.pojo.product.dto.AttributeTemplateAddNewDTO;
import cn.tedu.mall.pojo.product.dto.AttributeTemplateUpdateDTO;
import cn.tedu.mall.pojo.product.vo.AttributeTemplateDetailsVO;
import cn.tedu.mall.pojo.product.vo.AttributeTemplateListItemVO;
import cn.tedu.mall.pojo.product.vo.AttributeTemplateStandardVO;
import cn.tedu.mall.product.constant.WebConst;
import cn.tedu.mall.product.service.IAttributeTemplateService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>属性模板控制器</p>
 *
 * @author tedu.cn
 * @since 2021-11-30
 */
@Api(tags = "7. 属性模板管理模块")
@RestController
@RequestMapping("/pms/attribute-templates")
public class AttributeTemplateController {

    @Autowired
    private IAttributeTemplateService attributeTemplateService;

    /**
     * 增加属性模板
     */
    @ApiOperationSupport(order = 10)
    @ApiOperation(value = "增加属性模板", notes = "需要商品后台【写】权限：/pms/product/update")
    @PreAuthorize("hasAuthority('/pms/product/update')")
    @PostMapping("/addnew")
    public JsonResult<Void> addNew(@Valid AttributeTemplateAddNewDTO attributeTemplateAddnewDTO) {
        attributeTemplateService.addNew(attributeTemplateAddnewDTO);
        return JsonResult.ok();
    }

    /**
     * 删除属性模板
     */
    @ApiOperationSupport(order = 20)
    @ApiOperation(value = "删除属性模板", notes = "需要商品后台【写】权限：/pms/product/update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "属性模板id", paramType = "path", required = true, dataType = "long")
    })
    @PreAuthorize("hasAuthority('/pms/product/update')")
    @PostMapping("/{id:[0-9]+}/delete")
    public JsonResult<Void> deleteById(@PathVariable Long id) {
        attributeTemplateService.deleteById(id);
        return JsonResult.ok();
    }

    /**
     * 更新属性模板
     */
    @ApiOperationSupport(order = 30)
    @ApiOperation(value = "更新属性模板", notes = "需要商品后台【写】权限：/pms/product/update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "属性模板id", paramType = "path", required = true, dataType = "long")
    })
    @PreAuthorize("hasAuthority('/pms/product/update')")
    @PostMapping("/{id:[0-9]+}/update")
    public JsonResult<Void> updateById(@PathVariable Long id, @Valid AttributeTemplateUpdateDTO attributeTemplateUpdateDTO) {
        attributeTemplateService.updateById(id, attributeTemplateUpdateDTO);
        return JsonResult.ok();
    }

    /**
     * 查询属性模板详情（含其下的所有属性列表）
     */
    @ApiOperationSupport(order = 40)
    @ApiOperation(value = "查询属性模板详情（含其下的所有属性列表）", notes = "需要商品后台【读】权限：/pms/product/read")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "属性模板id", paramType = "path", required = true, dataType = "long")
    })
    @PreAuthorize("hasAuthority('/pms/product/read')")
    @GetMapping("/{id:[0-9]+}/details")
    public JsonResult<AttributeTemplateDetailsVO> getDetailsById(@PathVariable Long id) {
        AttributeTemplateDetailsVO attributeTemplateDetailsVO = attributeTemplateService.getDetailsById(id);
        return JsonResult.ok(attributeTemplateDetailsVO);
    }

    /**
     * 查询属性模板详情（含其下的销售属性列表）
     */
    @ApiOperationSupport(order = 41)
    @ApiOperation(value = "查询属性模板详情（含其下的销售属性列表）", notes = "需要商品后台【读】权限：/pms/product/read")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "属性模板id", paramType = "path", required = true, dataType = "long")
    })
    @PreAuthorize("hasAuthority('/pms/product/read')")
    @GetMapping("/{id:[0-9]+}/details/attribute-inclusion/sale")
    public JsonResult<AttributeTemplateDetailsVO> getDetailsIncludeSaleAttributeById(@PathVariable Long id) {
        AttributeTemplateDetailsVO attributeTemplateDetailsVO = attributeTemplateService.getDetailsIncludeSaleAttributeById(id);
        return JsonResult.ok(attributeTemplateDetailsVO);
    }

    /**
     * 查询属性模板详情（含其下的非销售属性列表）
     */
    @ApiOperationSupport(order = 42)
    @ApiOperation(value = "查询属性模板详情（含其下的非销售属性列表）", notes = "需要商品后台【读】权限：/pms/product/read")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "属性模板id", paramType = "path", required = true, dataType = "long")
    })
    @PreAuthorize("hasAuthority('/pms/product/read')")
    @GetMapping("/{id:[0-9]+}/details/attribute-inclusion/non-sale")
    public JsonResult<AttributeTemplateDetailsVO> getDetailsIncludeNonSaleAttributeById(@PathVariable Long id) {
        AttributeTemplateDetailsVO attributeTemplateDetailsVO = attributeTemplateService.getDetailsIncludeNonSaleAttributeById(id);
        return JsonResult.ok(attributeTemplateDetailsVO);
    }

    /**
     * 查询属性模板列表
     */
    @ApiOperationSupport(order = 43)
    @ApiOperation(value = "查询属性模板列表", notes = "需要商品后台【读】权限：/pms/product/read")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", dataType = "int")
    })
    @PreAuthorize("hasAuthority('/pms/product/read')")
    @GetMapping("")
    public JsonResult<JsonPage<AttributeTemplateStandardVO>> list(
            @RequestParam(required = false, defaultValue = WebConst.DEFAULT_PAGE) Integer page,
            @RequestParam(required = false, defaultValue = WebConst.DEFAULT_PAGE_SIZE) Integer pageSize) {
        JsonPage<AttributeTemplateStandardVO> attributeTemplates = attributeTemplateService.list(page, pageSize);
        return JsonResult.ok(attributeTemplates);
    }

    /**
     * 根据类别查询属性模板列表
     */
    @ApiOperationSupport(order = 44)
    @ApiOperation(value = "根据类别查询属性模板列表", notes = "需要商品后台【读】权限：/pms/product/read")
    @PreAuthorize("hasAuthority('/pms/product/read')")
    @GetMapping("/list-by-category")
    public JsonResult<List<AttributeTemplateListItemVO>> listBy(@RequestParam Long categoryId) {
        return JsonResult.ok(attributeTemplateService.listByCategoryId(categoryId));
    }

}
