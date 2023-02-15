package cn.tedu.mall.ams.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = {"cn.tedu.mall.ams.mapper"})
public class MybatisConfig {
}
