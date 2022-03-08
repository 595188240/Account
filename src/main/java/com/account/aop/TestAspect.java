package com.account.aop;

import com.account.annotations.Test;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author ffdeng2
 * @date 2022-3-4 16:59
 */
@Aspect
@Component
public class TestAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Pointcut("@annotation(com.account.annotations.Test)")
    public void TestAspect() {

    }

    @Around("TestAspect()")
    public Object aroundTest(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println(joinPoint);
        System.out.println(joinPoint.getTarget().toString());
        Signature signature = joinPoint.getSignature();
        System.out.println(signature.toString());
        // 获取入参
        System.out.println(Arrays.toString(joinPoint.getArgs()));
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        // 获取注解
        Test test = method.getAnnotation(Test.class);
        // 获取注解属性
        String value = test.value();
        System.out.println(value);

        Object proceed = joinPoint.proceed();

        return proceed;
    }

}
