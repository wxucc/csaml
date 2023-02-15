package cn.tedu.mall.resource.config;

import cn.tedu.mall.common.config.MallCommonConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({MallCommonConfiguration.class})
public class MallResourceConfiguration {
}
