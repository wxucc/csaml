package cn.tedu.mall.order.mapper;

import cn.tedu.mall.pojo.order.model.OmsOrderItem;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OmsOrderItemMapper {

    // 新增订单向(oms_order_item)的方法
    // 一个订单可能包含多个商品,每件商品都单独新增到数据库的话,连库次数多,效率低
    // 我们这里尽可能减少连库次数,实现一次将一个集合中所有的对象新增到数据库中
    // 所以我们当前方法的参数就设置为了List<OmsOrderItem>类型
    int insertOrderItemList(List<OmsOrderItem> omsOrderItems);


}
