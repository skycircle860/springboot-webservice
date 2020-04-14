package com.one.springboot.config;

import com.one.springboot.config.auth.LoginUser;
import com.one.springboot.config.auth.LoginUserArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;


//생성한 LoginUserArgumentResolver 를 스프링에서 인식될 수 있도록 클래스를 만들고 설정을 추가한다.
@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer{
    private final LoginUserArgumentResolver loginUserArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers){
        argumentResolvers.add(loginUserArgumentResolver);
    }
}
