package cn.tedu.mall.product.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis配置类
 */
@Configuration
@MapperScan(basePackages = {"cn.tedu.mall.product.mapper"})
public class MybatisConfiguration {
}
