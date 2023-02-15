package cn.tedu.mall.order;

import cn.tedu.mall.common.config.MallCommonConfiguration;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableDubbo
@Import({MallCommonConfiguration.class})
public class MallOrderWebApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallOrderWebApiApplication.class,args);
    }
}
