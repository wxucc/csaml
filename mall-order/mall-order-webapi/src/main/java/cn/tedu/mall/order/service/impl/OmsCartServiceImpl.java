package cn.tedu.mall.order.service.impl;

import cn.tedu.mall.common.exception.CoolSharkServiceException;
import cn.tedu.mall.common.pojo.domain.CsmallAuthenticationInfo;
import cn.tedu.mall.common.restful.JsonPage;
import cn.tedu.mall.common.restful.ResponseCode;
import cn.tedu.mall.order.mapper.OmsCartMapper;
import cn.tedu.mall.order.service.IOmsCartService;
import cn.tedu.mall.pojo.order.dto.CartAddDTO;
import cn.tedu.mall.pojo.order.dto.CartUpdateDTO;
import cn.tedu.mall.pojo.order.model.OmsCart;
import cn.tedu.mall.pojo.order.vo.CartStandardVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OmsCartServiceImpl implements IOmsCartService {

    @Autowired
    private OmsCartMapper omsCartMapper;

    @Override
    public void addCart(CartAddDTO cartDTO) {
        // 要查询当前登录用户的购物车中是否已经包含指定商品,要先获取当前登录用id
        // 单独编写一个方法,从SpringSecurity上下文中获取用户id
        Long userId=getUserId();
        // 先按照参数中用户id和skuId进行查询
        OmsCart omsCart=omsCartMapper
                .selectExistsCart(userId,cartDTO.getSkuId());
        // 判断当前用户购物车中是否已经有这个商品
        if(omsCart == null){
            // 如果不存在这个商品进行新增操作
            // 新增购物车需要的参数类型是OmsCart,所以要先实例化一个OmsCart对象
            OmsCart newCart=new OmsCart();
            // 将参数cartDTO中的同名属性赋值到newCart对象中
            BeanUtils.copyProperties(cartDTO,newCart);
            // cartDTO对象中没有userId属性,需要单独为newCart赋值
            newCart.setUserId(userId);
            // 执行新增操作,新增sku信息到购物车表
            omsCartMapper.saveCart(newCart);
        }else{
            // 如果已经存在执行数量的修改
            // 运行到这里omsCart一定不是null
            // 我们需要做的就是将购物车中原有的数量和本次新增的商品数量相加
            // 再赋值到当前的omsCart属性中去执行修改
            // 我们需要将omsCart对象的getQuantity()和cartDTO对象的getQuantity()相加
            omsCart.setQuantity(omsCart.getQuantity()+cartDTO.getQuantity());
            // 确定了数量之后,执行持久层方法进行修改
            omsCartMapper.updateQuantityById(omsCart);
        }
    }

    // 根据用户id分页查询该用户购物车中商品
    @Override
    public JsonPage<CartStandardVO> listCarts(Integer page, Integer pageSize) {
        // 首先要确定当前登录用的id,调用写好的方法从SpringSecurity上下文中获取
        Long userId=getUserId();
        // 执行查询之前,先设置分页条件
        PageHelper.startPage(page, pageSize);
        // 执行查询,在PageHelper的设置下,会自动在sql语句后添加limit关键字,实现分页查询
        List<CartStandardVO> list=omsCartMapper.selectCartsByUserId(userId);
        // list是分页数据,要将它保存到PageInfo对象中,再将PageInfo对象转换为JsonPage返回
        return JsonPage.restPage(new PageInfo<>(list));
    }

    // 支持批量删除的删除购物车信息的方法
    @Override
    public void removeCart(Long[] ids) {
        // 调用mapper中编写的按数组参数删除购物车信息的方法
        int row=omsCartMapper.deleteCartsByIds(ids);
        if(row==0){
            throw new CoolSharkServiceException(
                    ResponseCode.NOT_FOUND,"您要删除的商品已经删除了");
        }
    }

    @Override
    public void removeAllCarts() {
        Long userId=getUserId();
        int rows=omsCartMapper.deleteCartsByUserId(userId);
        if(rows==0){
            throw new CoolSharkServiceException(
                    ResponseCode.NOT_FOUND,"您的购物车已经是空的了!");
        }
    }

    @Override
    public void removeUserCarts(OmsCart omsCart) {
        // 直接调用删除购物车中商品方法即可
        // 我们电商网站不会因为购物车中商品不存在,就不让用户购买
        // 所以这个删除不判断是否成功,也不抛出异常
        omsCartMapper.deleteCartByUserIdAndSkuId(omsCart);
    }

    @Override
    public void updateQuantity(CartUpdateDTO cartUpdateDTO) {
        // 当前方法参数是CartUpdateDTO,执行修改需要的是OmsCart
        // 所以要先实例化OmsCart对象,并给它赋值
        OmsCart omsCart=new OmsCart();
        // cartUpdateDTO只有id和quantity属性,赋值给omsCart,执行修改的参数齐全了
        BeanUtils.copyProperties(cartUpdateDTO,omsCart);
        // 执行修改
        omsCartMapper.updateQuantityById(omsCart);

    }

    // 业务逻辑层方法中需要获得用户id
    // 我们的项目会在控制器方法运行前运行的过滤器代码中,对前端传入的表明用户身份的JWT解析
    // 解析后保存到SpringSecurity上下文中,我们可以从SpringSecurity上下文中获取用户信息
    public CsmallAuthenticationInfo getUserInfo(){
        // 编写获取SpringSecurity上下文的代码
        UsernamePasswordAuthenticationToken authenticationToken=
                (UsernamePasswordAuthenticationToken)
                SecurityContextHolder.getContext().getAuthentication();
        // 为了逻辑严谨,判断一下SpringSecurity获取的用户信息是否为null
        if(authenticationToken == null){
            throw new CoolSharkServiceException(
                    ResponseCode.UNAUTHORIZED,"您没有登录");
        }
        // 从SpringSecurity上下文中获取用户信息
        CsmallAuthenticationInfo csmallAuthenticationInfo=
                (CsmallAuthenticationInfo)
                authenticationToken.getCredentials();
        // 最后别忘了将用户信息返回
        return csmallAuthenticationInfo;
    }
    //  业务逻辑层中实际需求都是获取用户的id
    // 我们再一个方法,直接返回用户id,方便业务调用
    public Long getUserId(){
        return getUserInfo().getId();
    }

}








