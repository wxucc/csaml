package cn.tedu.mall.seckill.service.impl;

import cn.tedu.mall.common.exception.CoolSharkServiceException;
import cn.tedu.mall.common.pojo.domain.CsmallAuthenticationInfo;
import cn.tedu.mall.common.restful.ResponseCode;
import cn.tedu.mall.order.service.IOmsOrderService;
import cn.tedu.mall.pojo.order.dto.OrderAddDTO;
import cn.tedu.mall.pojo.order.dto.OrderItemAddDTO;
import cn.tedu.mall.pojo.order.vo.OrderAddVO;
import cn.tedu.mall.pojo.seckill.dto.SeckillOrderAddDTO;
import cn.tedu.mall.pojo.seckill.model.Success;
import cn.tedu.mall.pojo.seckill.vo.SeckillCommitVO;
import cn.tedu.mall.seckill.config.RabbitMqComponentConfiguration;
import cn.tedu.mall.seckill.service.ISeckillService;
import cn.tedu.mall.seckill.utils.SeckillCacheUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SeckillServiceImpl implements ISeckillService {

    // 秒杀业务中,需要使用redis的是判断用户重复购买和判断库存数,都是操作字符串的
    // 所以使用stringRedisTemplate
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    // 秒杀生成订单直接调用普通订单生成的方法即可,dubbo调用order模块
    @DubboReference
    private IOmsOrderService dubboOrderService;
    // 将秒杀成功信息发送给rabbitMQ
    @Autowired
    private RabbitTemplate rabbitTemplate;
    /*
    1.判断用户是否为重复购买和Redis中该Sku是否有库存
    2.秒杀订单转换成普通订单,需要使用dubbo调用order模块的生成订单方法
    3.使用消息队列(RabbitMQ)将秒杀成功记录信息保存到success表中
    4.秒杀订单信息返回
     */

    @Override
    public SeckillCommitVO commitSeckill(SeckillOrderAddDTO seckillOrderAddDTO) {
        // 第一阶段:判断用户是否为重复购买和Redis中该Sku是否有库存
        // 要从方法参数seckillOrderAddDTO中获取skuId
        Long skuId=seckillOrderAddDTO.getSeckillOrderItemAddDTO().getSkuId();
        // 从SpringSecurity上下文中获取用户Id
        Long userId=getUserId();
        // 我们明确了本次请求是哪个用户要购买哪个sku商品(userId和skuId的值)
        // 根据秒杀业务规则,一个用户每件sku只能购买1次
        // 所以我们可以结合userId和skuId生成一个检查重复购买的key
        // mall:seckill:reseckill:2:1
        String reSeckillCheckKey= SeckillCacheUtils.getReseckillCheckKey(skuId,userId);
        // 用上面的key向redis中发送命令,利用stringRedisTemplate的increment()方法
        // 可以实现下面效果
        // 1.如果上面的key在Redis中不存在,redis中会创建这个key,并生成一个值,值为1
        // 2.如果上面的key在Redis中存在,那么就会在当前数值的基础上再加1后保存
        //      例如已经是1了,就变为2保存起来
        // 3.无论这个key存在与否,都会将最后的值返回给程序
        // 综上,只有用户之前没有调用这个方法,返回值才为1,为1才表示用户是第一次购买这个sku
        Long seckillTimes=stringRedisTemplate
                .boundValueOps(reSeckillCheckKey).increment();
        // 如果seckillTimes值大于1,就是用户已经购买过了
        if(seckillTimes>100){
            // 抛出异常,提示不能重复购买,终止程序
            throw new CoolSharkServiceException(
                    ResponseCode.FORBIDDEN,"您已经购买过这个商品了,谢谢您的支持");
        }
        // 程序运行到此处,表示当前用户是第一次购买这个商品
        // 下面判断当前sku是否还有库存
        // 库存数是在缓存预热是加载到Redis中的,要获取对应sku的key
        // mall:seckill:sku:stock:2
        String skuStockKey=SeckillCacheUtils.getStockKey(skuId);
        // 判断这个key是否存在
        if(!stringRedisTemplate.hasKey(skuStockKey)){
            // 如果key不存在,抛出异常
            throw new CoolSharkServiceException(
                    ResponseCode.INTERNAL_SERVER_ERROR,"缓存中没有库存信息,购买失败");
        }
        // 下面判断库存数是否允许购买,使用stringRedisTemplate的decrement()方法
        // 这里的decrement()和increment()向反,效果是能够将key对应的值减1,然后返回
        Long leftStock=stringRedisTemplate.boundValueOps(skuStockKey).decrement();
        // leftStock是当前库存数减1之后返回的
        // 返回0时,表示当前用户购买到了最后一件库存商品
        // 只有返回值小于0,为负值时,才表示已经没有库存了
        /*if(leftStock<0){
            // 没有库存了,抛出异常终止
            // 但是要先将用户购买这个商品的记录恢复为0
            stringRedisTemplate.boundValueOps(reSeckillCheckKey).decrement();
            throw new CoolSharkServiceException(
                    ResponseCode.BAD_REQUEST,"对不起您购买的商品暂时售罄");
        }*/
        // 到此为止,用户通过了重复购买和库存数的判断,可以开始生成订单了
        // 第二阶段:秒杀订单转换成普通订单,需要使用dubbo调用order模块的生成订单方法
        // 目标是将参数SeckillOrderAddDTO转换成OrderAddDTO
        // 要观察这两个类的不同,然后编写转换方法完成转换
        // 转换方法代码较多,需要单独编写一个方法
        OrderAddDTO orderAddDTO=convertSeckillOrderToOrder(seckillOrderAddDTO);
        // 完成转换操作,订单的所有属性就都赋值完毕了
        // 但是userId要单独赋值,前端传入的参数中不会包含userId
        orderAddDTO.setUserId(userId);
        // dubbo调用order模块生成订单的方法,完成订单的新增
        OrderAddVO orderAddVO = dubboOrderService.addOrder(orderAddDTO);
        // 第三阶段:使用消息队列(RabbitMQ)将秒杀成功记录信息保存到success表中
        // 业务要求我们记录秒杀成功的信息,但是它并不需要立即运行,可以由消息队列完成
        // 我们要创建Success秒杀记录对象,然后将它发送给RabbitMQ
        Success success=new Success();
        // Success大部分属性和sku对象相同,可以做同名属性赋值
        BeanUtils.copyProperties(seckillOrderAddDTO.getSeckillOrderItemAddDTO(),
                                    success);
        // 把未赋值的必要信息补全(有些非必要信息已忽略)
        success.setUserId(userId);
        success.setSeckillPrice(seckillOrderAddDTO
                            .getSeckillOrderItemAddDTO().getPrice());
        success.setOrderSn(orderAddVO.getSn());
        // success对象赋值完备后,将发送给RabbitMQ
        rabbitTemplate.convertAndSend(
                RabbitMqComponentConfiguration.SECKILL_EX,
                RabbitMqComponentConfiguration.SECKILL_RK,
                success);
        // 第四阶段:秒杀订单信息返回
        // 返回值SeckillCommitVO和提交订单获得的返回值OrderAddVO属性完全一致
        // 直接把同名属性赋值之后返回即可
        SeckillCommitVO commitVO=new SeckillCommitVO();
        BeanUtils.copyProperties(orderAddVO,commitVO);
        // 修改返回值为commitVO

        return commitVO;
    }

    private OrderAddDTO convertSeckillOrderToOrder(SeckillOrderAddDTO seckillOrderAddDTO) {
        // 先实例化要返回的对象
        OrderAddDTO orderAddDTO=new OrderAddDTO();
        // 将参数seckillOrderAddDTO的同名属性赋值到orderAddDTO
        BeanUtils.copyProperties(seckillOrderAddDTO,orderAddDTO);
        // 经过观察两个对象的属性需要我们处理的实际上只有当前包含的订单项信息
        // 秒杀订单中只有一个SeckillOrderItemAddDTO属性
        // 常规订单中有一个List泛型是OrderItemAddDTO
        // 所以我们下面的操作时将SeckillOrderItemAddDTO转换成OrderItemAddDTO,并添加到list集合中
        OrderItemAddDTO orderItemAddDTO=new OrderItemAddDTO();
        // 同名属性赋值
        BeanUtils.copyProperties(seckillOrderAddDTO.getSeckillOrderItemAddDTO(),
                                    orderItemAddDTO);
        // 在向最终集合赋值前,先实例化普通订单项的泛型集合
        List<OrderItemAddDTO> list=new ArrayList<>();
        // 把赋值好的订单项对象,添加到这个集合中
        list.add(orderItemAddDTO);
        // 将集合对象赋值到orderAddDTO对象的orderItems属性中
        orderAddDTO.setOrderItems(list);
        // 最后别忘了返回
        return orderAddDTO;
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
