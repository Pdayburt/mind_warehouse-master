package com.mind.mind_warehouse.config;

import com.mind.mind_warehouse.interceptor.PermissionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    static final String ORIGINS[] = new String[] { "GET", "POST", "PUT", "DELETE" };
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new PermissionInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns("/manager/login")
//                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");
//    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**")
                //放行哪些原始域
                .allowedOrigins("*")
                //是否发送 Cookie
                .allowCredentials(true)
                //放行哪些请求方式
                .allowedMethods(ORIGINS)
                //放行哪些原始请求头部信息
                .allowedHeaders("*")
                //暴露哪些头部信息
                .exposedHeaders("*")
                .maxAge(3600);
    }
}
