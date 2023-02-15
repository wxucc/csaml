package cn.tedu.mall.order.controller;

import cn.tedu.mall.common.restful.JsonPage;
import cn.tedu.mall.common.restful.JsonResult;
import cn.tedu.mall.order.service.IOmsOrderService;
import cn.tedu.mall.pojo.order.dto.OrderAddDTO;
import cn.tedu.mall.pojo.order.dto.OrderListTimeDTO;
import cn.tedu.mall.pojo.order.dto.OrderStateUpdateDTO;
import cn.tedu.mall.pojo.order.vo.OrderAddVO;
import cn.tedu.mall.pojo.order.vo.OrderListVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rx.internal.operators.OnSubscribeTakeLastOne;

@RestController
@RequestMapping("/oms/order")
@Api(tags = "订单管理模块")
public class OmsOrderController {

    @Autowired
    private IOmsOrderService omsOrderService;

    @PostMapping("/add")
    @ApiOperation("执行新增订单的方法")
    @PreAuthorize("hasRole('user')")
    public JsonResult<OrderAddVO> addOrder(@Validated OrderAddDTO orderAddDTO){
        OrderAddVO orderAddVO=omsOrderService.addOrder(orderAddDTO);
        return JsonResult.ok(orderAddVO);
    }

    @GetMapping("/list")
    @ApiOperation("分页查询当前登录用户指定时间内订单")
    @PreAuthorize("hasRole('user')")
    public JsonResult<JsonPage<OrderListVO>> listUserOrders(
                                OrderListTimeDTO orderListTimeDTO){
        JsonPage<OrderListVO> jsonPage=
                omsOrderService.listOrdersBetweenTimes(orderListTimeDTO);
        return JsonResult.ok(jsonPage);

    }

    @PostMapping("/update/state")
    @ApiOperation("修改订单状态的方法")
    @PreAuthorize("hasRole('user')")
    public JsonResult updateOrderState(
            @Validated OrderStateUpdateDTO orderStateUpdateDTO){
        omsOrderService.updateOrderState(orderStateUpdateDTO);
        return JsonResult.ok("修改完成!");

    }

}
