package cn.tedu.mall;

import cn.tedu.mall.common.config.MallCommonConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@Import({MallCommonConfiguration.class})
public class MallPassportWebApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallPassportWebApiApplication.class,args);
    }
}
