package cn.tedu.mall.sso.security.config;

import cn.tedu.mall.sso.security.MyAccessDeniedHandler;
import cn.tedu.mall.sso.security.MyAuthenticationEntryPoint;
import cn.tedu.mall.sso.security.filter.SSOFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class SSOWebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private SSOFilter ssoFilter;
    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;
    @Autowired
    private MyAuthenticationEntryPoint myAuthenticationEntryPoint;
    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfigurationSource source =   new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");	//同源配置，*表示任何请求都视为同源，若需指定ip和端口可以改为如“localhost：8080”，多个以“，”分隔；
        corsConfiguration.addAllowedHeader("Authorization");//header，允许哪些header，本案中使用的是token，此处可将*替换为token；
        corsConfiguration.addAllowedMethod("*");	//允许的请求方法，PSOT、GET等
        ((UrlBasedCorsConfigurationSource) source).registerCorsConfiguration("/**",corsConfiguration); //配置允许跨域访问的url
        return source;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 放行清单
        String[] permitList = {
                "/swagger-resources/**",    // Knife4j在线API文档的资源
                "/v2/api-docs/**",          // Knife4j在线API文档的资源
                "/favicon.ico",     // 网站图标文件
                "/",                // 根页面，通常是主页
                "/*.html",          // 任何html
                "/**/*.html",       // 任何目录下的html
                "/**/*.css",        // 任何目录下的css
                "/**/*.js",         // 任何目录下的js
                "/*/sso/login",     // 登录
                "/*/sso/logout",    // 登录
                "/*/sso/checkLogin", // 验证登录
                "/*/sso/home"
        };
        // 禁止跨域请求伪造过滤器
        http.csrf().disable();
        http.cors().configurationSource(corsConfigurationSource());
        //关闭跨域
        CorsConfigurer<HttpSecurity> cors = http.cors();
        // Session管理
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
        http.sessionManagement().sessionFixation().none();
        // 认证规则
        http.authorizeRequests()
                .antMatchers(permitList).permitAll()
                .anyRequest().authenticated();
        //权限不足处理器
        http.exceptionHandling().accessDeniedHandler(myAccessDeniedHandler);
        //未认证处理器
        http.exceptionHandling().authenticationEntryPoint(myAuthenticationEntryPoint);
        // 添加自定义过滤器
        http.addFilterBefore(ssoFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
