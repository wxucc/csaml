package cn.tedu.mall;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class MallSeckillWebApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallSeckillWebApiApplication.class,args);
    }
}
