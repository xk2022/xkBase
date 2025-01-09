package com.xk.upms.aop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.AbstractCollection;

@Aspect
@Component
@Slf4j
public class ControllerAspect {

    @Pointcut("execution(* com.xk.upms.controller..*(..))")
    public void pointcut() { }

    @Before("pointcut()")
    public void logRequest(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String clazzName = signature.getDeclaringTypeName();
        String methodName = signature.getName();
        log.info(
                "[{}] - [{}] Request URI -> {}",
                clazzName,
                methodName,
                request.getRequestURI()
        );
        log.info(
                "[{}] - [{}] Request Method -> {}",
                clazzName,
                methodName,
                request.getMethod()
        );
        log.info(
                "[{}] - [{}] Request Parameters -> {}",
                clazzName,
                methodName,
                request.getQueryString()
        );
        log.info(
                "[{}] - [{}] Request 0-> {}",
                clazzName,
                methodName,
                getRequestBody(request)
        );
    }

    @AfterReturning(returning = "obj", pointcut = "pointcut()")
    public void doAfterReturning(JoinPoint joinPoint, Object obj) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String clazzName = signature.getDeclaringTypeName();
        String methodName = signature.getName();
        if(obj instanceof AbstractCollection){
            int size = ((AbstractCollection) obj).size();
            log.info(
                    "[{}] - [{}] Response Size -> {}",
                    clazzName,
                    methodName,
                    size
            );
        }
        log.info(
                "[{}] - [{}] Response -> {}",
                clazzName,
                methodName,
                obj
        );
    }

    private String getRequestBody(HttpServletRequest request) {
        StringBuilder requestBody = new StringBuilder();
        try {
            BufferedReader reader = request.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                requestBody.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return requestBody.toString();
    }

}
