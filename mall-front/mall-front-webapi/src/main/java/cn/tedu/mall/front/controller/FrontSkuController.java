package cn.tedu.mall.front.controller;

import cn.tedu.mall.common.restful.JsonResult;
import cn.tedu.mall.front.service.IFrontProductService;
import cn.tedu.mall.pojo.product.vo.SkuStandardVO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/front/sku")
@Api(tags = "前台商品sku模块")
public class FrontSkuController {
    @Autowired
    private IFrontProductService frontProductService;

    // localhost:10004/front/sku/1
    @GetMapping("/{spuId}")
    @ApiOperation("根据spuId查询sku列表")
    @ApiImplicitParam(value = "spuId",name="spuId",example = "1")
    public JsonResult<List<SkuStandardVO>> getSkusBySpuId(
                                     @PathVariable Long spuId){
        List<SkuStandardVO> list=
                frontProductService.getFrontSkusBySpuId(spuId);
        return JsonResult.ok(list);
    }

}
