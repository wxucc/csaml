package cn.tedu.mall.seckill.controller;

import cn.tedu.mall.common.exception.CoolSharkServiceException;
import cn.tedu.mall.common.restful.JsonResult;
import cn.tedu.mall.common.restful.ResponseCode;
import cn.tedu.mall.pojo.seckill.dto.SeckillOrderAddDTO;
import cn.tedu.mall.pojo.seckill.vo.SeckillCommitVO;
import cn.tedu.mall.seckill.exception.SeckillBlockHandler;
import cn.tedu.mall.seckill.exception.SeckillFallBack;
import cn.tedu.mall.seckill.service.ISeckillService;
import cn.tedu.mall.seckill.utils.SeckillCacheUtils;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("seckill")
@Api(tags = "执行秒杀模块")
public class SeckillController {

    @Autowired
    private ISeckillService seckillService;
    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/{randCode}")
    @ApiOperation("验证随机码并提交秒杀订单")
    @ApiImplicitParam(value = "随机码", name = "randCode", required = true)
    @PreAuthorize("hasRole('user')")
    @SentinelResource(value = "seckill",
            blockHandlerClass = SeckillBlockHandler.class,
            blockHandler = "seckillBlock", fallbackClass = SeckillFallBack.class,
            fallback = "seckillFallBack")
    public JsonResult<SeckillCommitVO> commitSeckill(
            @PathVariable String randCode,
            @Validated SeckillOrderAddDTO seckillOrderAddDTO
    ) {
        //先获取spuId
        Long spuId = seckillOrderAddDTO.getSpuId();
        //获取这个SpuId对应的随机码的Key
        String randCodeKey = SeckillCacheUtils.getRandCodeKey(spuId);
        //先判断这个Key是否存在
        if (redisTemplate.hasKey(randCodeKey)) {
            //如果key存在,获取redis中预热的随机码
            String redisRandCode = redisTemplate.boundValueOps(randCodeKey).get().toString();
            //判断前端发来的随机码和redis中的随机码是否一致
            if (!randCode.equals(redisRandCode)) {
                // 前端随机码和redis随机码不一致,抛出异常
                throw new CoolSharkServiceException(
                        ResponseCode.NOT_FOUND, "没有找到指定商品(随机码不匹配)"
                );
            }
            //运行到此处,表示随机码匹配调用业务逻辑层
            SeckillCommitVO commitVO =
                    seckillService.commitSeckill(seckillOrderAddDTO);
            return JsonResult.ok(commitVO);
        } else {
            //如果不存在抛出异常,终止程序
            throw new CoolSharkServiceException(
                    ResponseCode.NOT_FOUND, "没有找到指定商品"
            );
        }
    }
}

