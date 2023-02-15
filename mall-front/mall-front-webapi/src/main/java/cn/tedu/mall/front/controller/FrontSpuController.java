package cn.tedu.mall.front.controller;

import cn.tedu.mall.common.restful.JsonPage;
import cn.tedu.mall.common.restful.JsonResult;
import cn.tedu.mall.front.service.IFrontProductService;
import cn.tedu.mall.pojo.product.vo.AttributeStandardVO;
import cn.tedu.mall.pojo.product.vo.SpuListItemVO;
import cn.tedu.mall.pojo.product.vo.SpuStandardVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/front/spu")
@Api(tags = "前台商品spu模块")
public class FrontSpuController {
    @Autowired
    private IFrontProductService frontProductService;

    // localhost:10004/front/spu/list/3
    @GetMapping("/list/{categoryId}")
    @ApiOperation("根据分类id分页查询spu列表")
    @ApiImplicitParams({
            @ApiImplicitParam(value ="分类id",name="categoryId",example = "3"),
            @ApiImplicitParam(value ="页码",name="page",example = "1"),
            @ApiImplicitParam(value ="每页条数",name="pageSize",example = "2")
    })
    public JsonResult<JsonPage<SpuListItemVO>> listSpuByPage(
                @PathVariable Long categoryId,Integer page,Integer pageSize){
        JsonPage<SpuListItemVO> jsonPage=frontProductService
                .listSpuByCategoryId(categoryId, page, pageSize);
        return JsonResult.ok(jsonPage);
    }

    // localhost:10004/front/spu/1
    @GetMapping("/{spuId}")
    @ApiOperation("根据spuId查询spu信息")
    @ApiImplicitParam(value = "spuId",name = "spuId",example = "1")
    public JsonResult<SpuStandardVO> getFrontSpuById(@PathVariable Long spuId){
        SpuStandardVO spuStandardVO=
                frontProductService.getFrontSpuById(spuId);
        return JsonResult.ok(spuStandardVO);
    }

    // localhost:10004/front/spu/template/1
    @GetMapping("/template/{id}")
    @ApiOperation("根据spuId查询商品的所有规格和属性")
    @ApiImplicitParam(value = "spuId",name="id",example = "1")
    public JsonResult<List<AttributeStandardVO>> getAttributesBySpuId(
                        @PathVariable Long id){
        List<AttributeStandardVO> list=frontProductService
                .getSpuAttributesBySpuId(id);
        return JsonResult.ok(list);
    }

}



