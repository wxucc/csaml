package cn.tedu.mall.seckill.consumer;

import cn.tedu.mall.pojo.seckill.model.Success;
import cn.tedu.mall.seckill.config.RabbitMqComponentConfiguration;
import cn.tedu.mall.seckill.mapper.SeckillSkuMapper;
import cn.tedu.mall.seckill.mapper.SuccessMapper;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RabbitListener(queues = RabbitMqComponentConfiguration.SECKILL_QUEUE)
public class SeckillQueueConsumer {

    @Autowired
    private SeckillSkuMapper seckillSkuMapper;

    @Autowired
    private SuccessMapper successMapper;

    //下面方法时队列接收到消息时运行的方法
    @RabbitHandler
    public void process(Success success){
        //先减少库存
        seckillSkuMapper.updateReduceStockBySkuId(success.getSkuId(),
                success.getQuantity());
        //新增Success对象到数据库
        successMapper.saveSuccess(success);
        //如果上面两个数据库操作发生异常引发了事务问题
        //1.如果不要求精确统计,不处理也可以
        //2如果要求精确统计首先可以编写try-catch快进行连库操作重试
        //如果重试失败,可以将失败的情况汇总后,提交到死信队列
        //因为死信队列是人工处理,所以效率不能保证,实际开发中要慎重使用死信队列
    }
}