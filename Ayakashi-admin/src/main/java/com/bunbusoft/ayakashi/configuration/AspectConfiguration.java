package com.bunbusoft.ayakashi.configuration;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Aspect
@Configuration
public class AspectConfiguration {
    @Before("execution(* com.bunbusoft.ayakashi.repository.*.*(..))")
    public void before(JoinPoint joinPoint){
        log.warn("before edit query of function:{}", joinPoint);
    }
}
