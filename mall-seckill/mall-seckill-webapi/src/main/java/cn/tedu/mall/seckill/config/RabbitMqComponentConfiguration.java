package cn.tedu.mall.seckill.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 生成必要的rabbtimq组件
 * 1个交换机
 * 1个队列
 * 1个路由key值
 */
@Configuration
public class RabbitMqComponentConfiguration {
    public static final String SECKILL_EX="seckill_ex";
    public static final String SECKILL_QUEUE="seckill_queue";
    public static final String SECKILL_RK="seckill_routing_key";
    @Bean
    public Queue seckillQueue(){
        return new Queue(SECKILL_QUEUE);
    }
    @Bean
    public DirectExchange seckillExchange(){
        return new DirectExchange(SECKILL_EX);
    }
    @Bean
    public Binding seckillBinding(){
        return BindingBuilder.bind(seckillQueue()).to(seckillExchange()).with(SECKILL_RK);
    }
}
