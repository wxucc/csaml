package cn.tedu.mall.ams;

import cn.tedu.mall.common.config.MallCommonConfiguration;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableDubbo
@Import(MallCommonConfiguration.class)
public class MallAmsWebApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallAmsWebApiApplication.class,args);
    }
}
