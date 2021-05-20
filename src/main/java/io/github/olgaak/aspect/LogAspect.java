package io.github.olgaak.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    private static final Logger logger
            = LoggerFactory.getLogger(LogAspect.class);

    @Before("execution(* io.github.olgaak.service.impl.*.*(..))")
    public void afterServiceMethodInvocation(JoinPoint jp){
        System.out.println("Test aop");
        logger.info("Service method was invoked " + jp.getSignature());
    }
}
