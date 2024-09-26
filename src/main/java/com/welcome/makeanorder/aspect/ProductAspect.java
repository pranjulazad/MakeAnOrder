package com.welcome.makeanorder.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@Slf4j
public class ProductAspect {
    @Pointcut("this(com.welcome.home.service.ProductService)")
    public void productServiceLogDetails(){}

//    @Pointcut("execution(* com.welcome.home.service.ProductService.*(..))")
//    public void productServiceLogDetails(){}

    @Around("productServiceLogDetails()")
    public Object beforeProductServiceLogDetails(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        log.info(joinPoint.getSignature().getClass().toString());

        Object object =  joinPoint.proceed();

        log.info("Product signature: {}", object);

        stopWatch.stop();

        log.info("Time taken by Product Service: {}ms", stopWatch.getTotalTimeMillis());

        return object;
    }

}
