package cn.tedu.mall;

import cn.tedu.mall.common.config.MallCommonConfiguration;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;
import org.springframework.context.annotation.Import;

@SpringBootApplication(exclude = ElasticsearchDataAutoConfiguration.class)
@EnableDubbo
@Import({MallCommonConfiguration.class})
public class MallUmsWebApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallUmsWebApiApplication.class,args);
    }
}
