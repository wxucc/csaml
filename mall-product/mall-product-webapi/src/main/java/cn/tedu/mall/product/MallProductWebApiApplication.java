package cn.tedu.mall.product;

import cn.tedu.mall.common.config.MallCommonConfiguration;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(MallCommonConfiguration.class)
@EnableDubbo
public class MallProductWebApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallProductWebApiApplication.class, args);
    }

}
