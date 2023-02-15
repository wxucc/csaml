package cn.tedu.mall.order.mapper;

import cn.tedu.mall.pojo.order.dto.OrderListTimeDTO;
import cn.tedu.mall.pojo.order.model.OmsOrder;
import cn.tedu.mall.pojo.order.vo.OrderListVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OmsOrderMapper {

    // 新增订单的方法
    int  insertOrder(OmsOrder omsOrder);
    // 查询当前登录用户指定时间范围内的所有订单(是关联订单项表的查询)
    List<OrderListVO> selectOrdersBetweenTimes(OrderListTimeDTO orderListTimeDTO);

    // 利用动态sql语句,实现对订单字段的修改
    // 参数是OmsOrder类型,必须包含id属性值,id属性值不能修改
    int updateOrderById(OmsOrder order);


}
