package org.example.backend.config;

import org.example.backend.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Resource
    private AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/**")   // 默认拦截所有请求
                .excludePathPatterns(
                        "/login",         // 放行登录界面
                        "/health",        // 放行健康检查
                        "/users");        // 放行用户注册
    }
}
