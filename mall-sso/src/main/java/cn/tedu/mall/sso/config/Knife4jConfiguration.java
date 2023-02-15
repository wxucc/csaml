package cn.tedu.mall.sso.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * Knife4j（Swagger2）的配置
 */
@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfiguration {
    @Bean(value = "sso")
    public Docket sso() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("Cool Shark Mall SSO单点登录认证中心在线API")
                        .description("Cool Shark Mall SSO单点登录认证中心在线API")
                        .termsOfServiceUrl("")
                        .version("1.0")
                        .build())
                //分组名称
                .groupName("JSD组")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("cn.tedu.mall.sso.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

}
