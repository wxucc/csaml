package cn.tedu.mall.order.controller;

import cn.tedu.mall.common.restful.JsonPage;
import cn.tedu.mall.common.restful.JsonResult;
import cn.tedu.mall.order.service.IOmsCartService;
import cn.tedu.mall.order.utils.WebConsts;
import cn.tedu.mall.pojo.order.dto.CartAddDTO;
import cn.tedu.mall.pojo.order.dto.CartUpdateDTO;
import cn.tedu.mall.pojo.order.vo.CartStandardVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/oms/cart")
@Api(tags = "购物车管理模块")
public class OmsCartController {
    @Autowired
    private IOmsCartService omsCartService;

    @PostMapping("/add")
    @ApiOperation("新增sku信息到购物车")
    // 在运行本控制器方法前,已经经过了过滤器的代码, 过滤器中解析的前端传入的JWT
    // 解析正确后,会将用户信息保存在SpringSecurity上下文中
    // 酷鲨商城前台用户登录时,登录代码中会给用户赋予一个固定的权限名称ROLE_user
    // 下面的注解就是在判断登录的用户是否具备这个权限,其实主要作用还是判断用户是否登录
    // 这个注解的效果是从SpringSecurity中判断当前登录用户权限,
    // 如果没登录返回401,权限不匹配返回403
    @PreAuthorize("hasAuthority('ROLE_user')")
    // @Validated注解是激活SpringValidation框架用的
    // 参数CartAddDTO中,有多个属性设置了非空的验证规则,如果有设置了规则的属性为null
    // 会抛出BindException异常,终止方法调用,运行全局异常处理类中对应的方法
    public JsonResult addCart(@Validated CartAddDTO cartAddDTO){
        omsCartService.addCart(cartAddDTO);
        return JsonResult.ok("新增sku到购物车完成");
    }

    @GetMapping("/list")
    @ApiOperation("根据用户id分页查询购物车中sku列表")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "页码",name="page",example = "1"),
            @ApiImplicitParam(value = "每页条数",name="pageSize",example = "1")
    })
    @PreAuthorize("hasAuthority('ROLE_user')")
    public JsonResult<JsonPage<CartStandardVO>> listCartsByPage(
      @RequestParam(required = false,defaultValue = WebConsts.DEFAULT_PAGE)
                                                    Integer page,
      @RequestParam(required = false,defaultValue = WebConsts.DEFAULT_PAGE_SIZE)
                                                    Integer pageSize
    ){
        JsonPage<CartStandardVO> jsonPage=
                omsCartService.listCarts(page,pageSize);
        return JsonResult.ok(jsonPage);
    }

    @PostMapping("/delete")
    @ApiOperation("根据id数组删除购物车sku信息")
    @ApiImplicitParam(value = "要删除的id数组",name="ids",
                                required = true,dataType = "array")
    @PreAuthorize("hasAuthority('ROLE_user')")
    public JsonResult removeCartsByIds(Long[] ids){
        omsCartService.removeCart(ids);
        return JsonResult.ok("删除完成");
    }

    @PostMapping("/update/quantity")
    @ApiOperation("修改购物车中sku的数量")
    @PreAuthorize("hasAuthority('ROLE_user')")
    public JsonResult updateQuantity(@Validated CartUpdateDTO cartUpdateDTO){
        omsCartService.updateQuantity(cartUpdateDTO);
        return JsonResult.ok("修改完成!");
    }

    @PostMapping("/delete/all")
    @ApiOperation("清空当前登录用户购物车信息")
    // SpringSecurity框架用一个数组保存权限和角色,规范上所有角色都用ROLE_开头来标识
    // @PreAuthorize("hasAuthority('xxx')")是来判断这个数组中是否具备某个权限或角色的
    // @PreAuthorize("hasRole('yyy')")是专门用来判断数组中是否具备某个角色的
    // 实际上判断的是在数组中是否有ROLE_yyy的资格
    // 所以下面两个注解在运行判断时是等价的
    // @PreAuthorize("hasAuthority('ROLE_user')")
    @PreAuthorize("hasRole('user')")
    public JsonResult removeCartsByUserId(){
        omsCartService.removeAllCarts();
        return JsonResult.ok("购物车已清空");
    }


}








