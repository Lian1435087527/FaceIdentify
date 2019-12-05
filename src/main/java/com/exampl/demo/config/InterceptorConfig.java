package com.exampl.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.exampl.demo.interceptor.AuthenticationInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor())
                .addPathPatterns("/faceDetect.html/**")
                .addPathPatterns("/modelcon.html/**")
                .addPathPatterns("/modelup.html/**")
                //.addPathPatterns("picdown.html/**")
                //.addPathPatterns("/picup.html/**")
                .addPathPatterns("/index.html/**")
                .addPathPatterns("/icons.html/**")
                .addPathPatterns("/ui-elements.html/**")
                .addPathPatterns("/graphs.html/**")
                .addPathPatterns("/forms.html/**");
    }
    @Bean
    public AuthenticationInterceptor authenticationInterceptor() {
        return new AuthenticationInterceptor();
    }
}
