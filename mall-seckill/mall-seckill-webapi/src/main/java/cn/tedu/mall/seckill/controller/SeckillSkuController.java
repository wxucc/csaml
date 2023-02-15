package cn.tedu.mall.seckill.controller;

import cn.tedu.mall.common.restful.JsonResult;
import cn.tedu.mall.pojo.seckill.vo.SeckillSkuVO;
import cn.tedu.mall.seckill.service.ISeckillSkuService;
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
@RequestMapping("/seckill/sku")
@Api(tags = "秒杀Sku模块")
public class SeckillSkuController {

    @Autowired
    private ISeckillSkuService seckillSkuService;

    // localhost:10007/seckill/sku/list/2
    @GetMapping("/list/{spuId}")
    @ApiOperation("根据spuId查询sku列表")
    @ApiImplicitParam(value = "spuId" ,name="spuId",example = "2")
    public JsonResult<List<SeckillSkuVO>> listSeckillSkus(
                                            @PathVariable Long spuId){
        List<SeckillSkuVO> list = seckillSkuService.listSeckillSkus(spuId);
        return JsonResult.ok(list);
    }


}
