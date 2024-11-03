package com.thuynh.lab4.aspect;

import com.thuynh.lab4.service.LoggerService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {
    @Autowired
    LoggerService loggerService;

    @Pointcut("execution( * com.thuynh.lab4.controller..*(..))")
    public void logMe() {
    }

    @After("logMe()")
    public void logAfter(JoinPoint joinPoint) {
        loggerService.log(joinPoint.getSignature().getName());
    }

    @AfterThrowing("logMe()")
    public void logAfterThrowing(JoinPoint joinPoint) {
        loggerService.log("Exception " + joinPoint.getSignature().getName());
    }
}
