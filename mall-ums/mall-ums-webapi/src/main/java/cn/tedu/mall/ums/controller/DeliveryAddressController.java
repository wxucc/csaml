package cn.tedu.mall.ums.controller;


import cn.tedu.mall.common.restful.JsonPage;
import cn.tedu.mall.common.restful.JsonResult;
import cn.tedu.mall.pojo.ums.dto.DeliveryAddressAddDTO;
import cn.tedu.mall.pojo.ums.dto.DeliveryAddressEditDTO;
import cn.tedu.mall.pojo.ums.vo.DeliveryAddressStandardVO;
import cn.tedu.mall.ums.service.IDeliveryAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户收货地址表 前端控制器
 * </p>
 *
 * @author tedu.cn
 * @since 2022-02-22
 */
@RestController
@RequestMapping("/ums/deliveryAddress")
@Api(tags = "地址管理模块")
public class DeliveryAddressController {
    @Autowired
    private IDeliveryAddressService deliveryAddressService;
    /**
     * 根据userId查询地址
     */
    @ApiOperation(value="根据登录用户查询管理地址列表")
    @GetMapping("list")
    @PreAuthorize("hasRole('ROLE_user')")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", dataType = "int")
    })
    public JsonResult<JsonPage<DeliveryAddressStandardVO>> listAddress(Integer page, Integer pageSize){
        JsonPage<DeliveryAddressStandardVO> addresses=deliveryAddressService.listAddress(page,pageSize);
        return JsonResult.ok(addresses);
    }
    /**
     * 新增地址
     */
    @ApiOperation(value="新增用户邮寄地址")
    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_user')")
    public JsonResult addAddress(DeliveryAddressAddDTO deliveryAddressAddDTO){
        deliveryAddressService.addAddress(deliveryAddressAddDTO);
        return JsonResult.ok();
    }
    /**
     * 编辑地址
     */
    @ApiOperation(value="对已有地址进行编辑")
    @PostMapping("/edit")
    @PreAuthorize("hasRole('ROLE_user')")
    public JsonResult editAddress(DeliveryAddressEditDTO deliveryAddressEditDTO){
        deliveryAddressService.editAddress(deliveryAddressEditDTO);
        return JsonResult.ok();
    }
    /**
     * 删除已有地址
     */
    @ApiOperation(value="根据id删除已有地址")
    @PostMapping("/delete")
    @PreAuthorize("hasRole('ROLE_user')")
    public JsonResult deleteAddress(Long id){
        deliveryAddressService.deleteAddress(id);
        return JsonResult.ok();
    }

}
