package com.fabrick.bank.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.fabrick.bank.account.*.*Controller.*(..)) " +
            "|| execution(* com.fabrick.bank.account.*.*Service.*(..))" +
            "|| execution(* com.fabrick.bank.account.*.*Repository.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        log.info("Request received in " + className + "." + methodName + " with arguments: " + Arrays.toString(args));
    }

    @AfterReturning(pointcut = "execution(* com.fabrick.bank.account.*.*Controller.*(..))" +
            "|| execution(* com.fabrick.bank.account.*.*Service.*(..))" +
            "|| execution(* com.fabrick.bank.account.*.*Repository.*(..))", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        log.info("Response from " + className + "." + methodName + ": " + result);
    }
}