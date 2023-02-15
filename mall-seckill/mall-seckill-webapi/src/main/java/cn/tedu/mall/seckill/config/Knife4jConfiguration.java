package cn.tedu.mall.seckill.config;

import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * Knife4j（Swagger2）的配置
 */
@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfiguration {

    @Autowired
    private OpenApiExtensionResolver openApiExtensionResolver;

    @Bean(value = "seckill")
    public Docket pms() {
        String groupName = "1.0.0";
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .host("http://www.tedu.cn")
                .apiInfo(apiInfo())
                .groupName(groupName) //分组名称
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.tedu.mall.seckill.controller")) //这里指定Controller扫描包路径
                .paths(PathSelectors.any())
                .build()
                .extensions(openApiExtensionResolver.buildExtensions(groupName));
        return docket;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Cool Shark Mall SSO秒杀功能API")
                .description("Cool Shark Mall SSO秒杀功能API")
                .termsOfServiceUrl("http://www.tedu.cn")
                .contact(new Contact("jsd@tedu.cn", "http://jsd.tedu.cn", "jsd@tedu.cn"))
                .version("1.0.0")
                .build();
    }

}
