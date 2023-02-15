package cn.tedu.mall.common.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 当前模块（mall-common）的配置类，当其它模块依赖此模块时，应导入此配置类
 */
@Configuration
@ComponentScan({
        "cn.tedu.mall.common.exception.handler",
        "cn.tedu.mall.common.utils"})
public class MallCommonConfiguration {
}