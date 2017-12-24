package com.shy.springbootskeleton.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.Arrays;

@Aspect
@Order(2)
@Configuration
@Slf4j
public class LoggingAspect {
    /**
     * 这个方法似乎是想一个虚拟的lock/密令一样,配置好AOP参数后,使用
     */
    @Pointcut("execution(* com.shy.springbootskeleton.service..*.*(..))" +
            "||@annotation(org.springframework.web.bind.annotation.RequestMapping)" +
            "||execution(* com.shy.springbootskeleton.dao..*.*(..))")
    public void declareJoinPointExpression(){

    }
    @Before("declareJoinPointExpression()")
    public void befroeMethod(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();//取得方法参数.(拦截器只能获取action的参数, 但对action的使用很在行
        log.info("The method [ {} ] begins with Parameters: {}", joinPoint.getSignature(), Arrays.toString(args));
    }

    @AfterReturning(value = "declareJoinPointExpression()", returning = "result")
    public void afterMethodReturn(JoinPoint joinPoint, Object result) {
        log.info("The method [ {} ] ends with Result: {}", joinPoint.getSignature(), result);
    }

    @AfterThrowing(value = "declareJoinPointExpression()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        log.error("Error happened in method: [ {} ]", joinPoint.getSignature());
        log.error("Parameters: {}", Arrays.toString(joinPoint.getArgs()));
        log.error("Exception StackTrace: {}", e);
    }

}
