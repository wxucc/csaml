package cn.tedu.mall.order.service.impl;

import cn.tedu.mall.common.exception.CoolSharkServiceException;
import cn.tedu.mall.common.pojo.domain.CsmallAuthenticationInfo;
import cn.tedu.mall.common.restful.JsonPage;
import cn.tedu.mall.common.restful.ResponseCode;
import cn.tedu.mall.order.mapper.OmsOrderItemMapper;
import cn.tedu.mall.order.mapper.OmsOrderMapper;
import cn.tedu.mall.order.service.IOmsCartService;
import cn.tedu.mall.order.service.IOmsOrderService;
import cn.tedu.mall.order.utils.IdGeneratorUtils;
import cn.tedu.mall.pojo.order.dto.OrderAddDTO;
import cn.tedu.mall.pojo.order.dto.OrderItemAddDTO;
import cn.tedu.mall.pojo.order.dto.OrderListTimeDTO;
import cn.tedu.mall.pojo.order.dto.OrderStateUpdateDTO;
import cn.tedu.mall.pojo.order.model.OmsCart;
import cn.tedu.mall.pojo.order.model.OmsOrder;
import cn.tedu.mall.pojo.order.model.OmsOrderItem;
import cn.tedu.mall.pojo.order.vo.OrderAddVO;
import cn.tedu.mall.pojo.order.vo.OrderDetailVO;
import cn.tedu.mall.pojo.order.vo.OrderListVO;
import cn.tedu.mall.product.service.order.IForOrderSkuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// 订单管理模块的业务逻辑层实现类,因为后期秒杀模块也是要生成订单的,需要dubbo调用这个方法
@DubboService
@Service
@Slf4j
public class OmsOrderServiceImpl implements IOmsOrderService {

    // dubbo调用减少库存的方法
    @DubboReference
    private IForOrderSkuService dubboSkuService;
    @Autowired
    private IOmsCartService omsCartService;
    @Autowired
    private OmsOrderMapper omsOrderMapper;
    @Autowired
    private OmsOrderItemMapper omsOrderItemMapper;

    // 新增订单的方法
    // 这个方法中利用Dubbo远程调用了product模块的数据库操作,有分布式事务需求
    // 所以使用注解激活Seata分布式事务的功能
    @GlobalTransactional
    @Override
    public OrderAddVO addOrder(OrderAddDTO orderAddDTO) {
        // 第一部分:收集信息,准备数据
        // 先实例化OmsOrder对象,最终实现新增订单到数据库的对象就是它
        OmsOrder order=new OmsOrder();
        // 将参数orderAddDTO中的同名属性赋值到order对象中
        BeanUtils.copyProperties(orderAddDTO,order);
        // orderAddDTO中包含的属性并不齐全,还有一些是可空内容,
        // order对象现在还缺失属性,我们可以编写一个方法来专门收集或生成
        loadOrder(order);
        // 运行完上面的方法,order对象的所有属性就都有值了
        // 下面开始整理收集参数orderAddDTO中包含的订单项集合:orderItems属性
        // 首先从参数中获得这个集合
        List<OrderItemAddDTO> itemAddDTOs=orderAddDTO.getOrderItems();
        if(itemAddDTOs ==null  || itemAddDTOs.isEmpty()){
            // 如果订单参数中没有订单项信息,直接抛出异常,终止程序
            throw new CoolSharkServiceException(
                    ResponseCode.BAD_REQUEST,"订单中至少包含一件商品");
        }
        // 我们需要完成订单项信息新增到数据库的功能,而操作数据库方法的参数是List<OmsOrderItem>
        // 但是现在集合的类型是List<OrderItemAddDTO>,先需要将这个集合中的元素转换,保存到新集合
        List<OmsOrderItem> omsOrderItems=new ArrayList<>();
        // 编写从参数中获取的集合
        for(OrderItemAddDTO addDTO : itemAddDTOs){
            // 先实例化最终需要的类型对象OmsOrderItem
            OmsOrderItem orderItem=new OmsOrderItem();
            // 将正在遍历的addDTO对象的同名属性赋值到orderItem
            BeanUtils.copyProperties(addDTO,orderItem);
            // addDTO对象中没有id属性和orderId属性,需要单独赋值
            // 当前对象的id属性仍然从leaf中获取
            Long itemId=IdGeneratorUtils.getDistributeId("order_item");
            orderItem.setId(itemId);
            // 赋值当前正要新增的订单id
            orderItem.setOrderId(order.getId());
            // orderItem所有值都赋值完成了,将它保存到集合中
            omsOrderItems.add(orderItem);
            // 第二部分:执行数据库操作指令
            // 1.减少库存
            // 当前正在标记的对象就是一个包含SkuId和减少库存数的对象
            // 获取skuId
            Long skuId=orderItem.getSkuId();
            // dubbo调用减少库存的方法
            int row=dubboSkuService.reduceStockNum(
                                    skuId,orderItem.getQuantity());
            // 判断row的值
            if(row==0){
                // 如果row的值为0,表示库存没有变化,库存不足导致的
                log.error("商品库存不足,skuId:{}",skuId);
                // 抛出异常,终止程序,会触发seata分布式事务的回滚
                throw new CoolSharkServiceException(
                        ResponseCode.BAD_REQUEST,"您要购买的商品库存不足!");
            }
            // 2.删除勾选的购物车商品信息
            OmsCart omsCart=new OmsCart();
            omsCart.setUserId(order.getUserId());
            omsCart.setSkuId(skuId);
            // 执行删除
            omsCartService.removeUserCarts(omsCart);
        }
        // 3.执行新增订单
        omsOrderMapper.insertOrder(order);
        // 4.新增订单项(批量新增集合中的所有订单项数据)
        omsOrderItemMapper.insertOrderItemList(omsOrderItems);
        // 第三部分:准备返回值,返回给前端
        // 实例化返回值类型对象
        OrderAddVO addVO=new OrderAddVO();
        // 给各个属性赋值
        addVO.setId(order.getId());
        addVO.setSn(order.getSn());
        addVO.setCreateTime(order.getGmtCreate());
        addVO.setPayAmount(order.getAmountOfActualPay());
        // 别忘了返回addVO
        return addVO;
    }

    // 为order对象补齐属性的方法
    private void loadOrder(OmsOrder order) {
        // order对象中的id和sn是没有被赋值的可能的,因为参数中根本就没有同名属性
        // 先给id赋值,这个id是从Leaf分布式序列号生成系统中获取的
        Long id= IdGeneratorUtils.getDistributeId("order");
        order.setId(id);
        // 再给sn赋值,sn赋值的原则是生成一个UUID,是给用户看的订单号
        order.setSn(UUID.randomUUID().toString());
        // 给userId赋值
        // 后期的秒杀业务也会调用当前类的新增订单方法,userId属性会在秒杀业务中被赋值
        // 因为dubbo远程调用时不能同时发送当前登录用户信息,
        // 所以我们要判断一下order中的userId是否为null
        if(order.getUserId() ==null){
            // 如果userId为null 就需要从SpringSecurity上下文获取用户id
            order.setUserId(getUserId());
        }

        // 可以将OrderAddDTO类中未被设置为非空的属性,进行null的验证
        // 这里以state属性为例,如果state为null 默认值设置为0
        if(order.getState()==null){
            order.setState(0);
        }

        // 为下单时间赋值,赋值当前时间即可
        // 为了保证gmt_order和gmt_create时间一致
        // 这里为它们赋值相同的时间
        LocalDateTime now=LocalDateTime.now();
        order.setGmtOrder(now);
        order.setGmtCreate(now);
        order.setGmtModified(now);

        // 最后处理实际支付金额的计算,返回给前端,用于和前端计算的金额进行验证
        // 实际支付金额=原价-优惠+运费
        // 所有和金额价格等钱相关的数据,为了防止浮点偏移,都要使用BigDecimal类型
        BigDecimal price=order.getAmountOfOriginalPrice();
        BigDecimal freight=order.getAmountOfFreight();
        BigDecimal discount=order.getAmountOfDiscount();
        BigDecimal actualPay=price.add(freight).subtract(discount);
        // 计算得到的实际支付金额,赋值给order对象
        order.setAmountOfActualPay(actualPay);

    }

    // 根据订单id,修改订单状态
    @Override
    public void updateOrderState(OrderStateUpdateDTO orderStateUpdateDTO) {
        // 实例化OmsOrder对象
        OmsOrder order=new OmsOrder();
        // 将orderStateUpdateDTO中同名属性赋值到order
        BeanUtils.copyProperties(orderStateUpdateDTO,order);
        // 调用动态修改订单的方法,修改订单状态
        omsOrderMapper.updateOrderById(order);
    }

    // 分页查询当前登录用户,在指定时间范围内的所有订单
    // 默认情况下查询最近一个月的订单,查询的返回值OrderListVO,它既包含订单信息也包含订单中的商品信息
    // 实现上面查询效果需求持久层编写特殊的关联查询和关联关系配置
    @Override
    public JsonPage<OrderListVO> listOrdersBetweenTimes(OrderListTimeDTO orderListTimeDTO) {
        // 方法开始第一步,先确定要查询的时间范围
        // 编写一个方法,判断orderListTimeDTO参数中时间的各种情况
        validateTimeAndLoadTime(orderListTimeDTO);
        // 获取userId赋值到参数中
        orderListTimeDTO.setUserId(getUserId());
        // 设置分页条件
        PageHelper.startPage(orderListTimeDTO.getPage(),
                             orderListTimeDTO.getPageSize());
        List<OrderListVO> list = omsOrderMapper.selectOrdersBetweenTimes(orderListTimeDTO);
        // 别忘了返回
        return JsonPage.restPage(new PageInfo<>(list));
    }

    private void validateTimeAndLoadTime(OrderListTimeDTO orderListTimeDTO) {
        // 获取参数中开始时间和结束时间
        LocalDateTime start=orderListTimeDTO.getStartTime();
        LocalDateTime end=orderListTimeDTO.getEndTime();
        // 为了不让业务更复杂,我们设计当start和end任意一个是null值时,就差最近一个月订单
        if (start == null || end == null){
            // start设置为一个月前的时间
            start=LocalDateTime.now().minusMonths(1);
            // end设置为当前时间即可
            end=LocalDateTime.now();
            // 将开始时间和结束时间赋值到参数中
            orderListTimeDTO.setStartTime(start);
            orderListTimeDTO.setEndTime(end);
        }else{
            // 如果start和end都非null
            // 就要判断start是否小于end,如果不小于就要抛出异常
            // if( end.isBefore(start))
            if(end.toInstant(ZoneOffset.of("+8")).toEpochMilli()<
                start.toInstant(ZoneOffset.of("+8")).toEpochMilli()){
                // 上面的判断是有时区修正的
                // 如果结束时间小于开始时间就要抛出异常
                throw new CoolSharkServiceException(ResponseCode.BAD_REQUEST,
                        "结束时间应大于开始时间");
            }
        }
    }

    @Override
    public OrderDetailVO getOrderDetail(Long id) {
        return null;
    }

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
