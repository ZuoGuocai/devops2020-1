package com.ypc.devopstest.aspect;

import com.ypc.devopstest.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.UUID;

@Slf4j
@Aspect
@Component
@SuppressWarnings("all")
public class AutoLogAspect {

    private static final String  UUID_NAME = "uuid-request";
    private static final String  TIMESTAMP_NAME = "timestamp-request";

    //Controller层切点
    @Pointcut("@annotation(com.ypc.devopstest.annotation.AutoLog)")
    public void autoLogAspect(){
    }

    @Before("autoLogAspect()")
    public void doBefore(JoinPoint joinPoint){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes)requestAttributes).getRequest();

        UUID uuid = UUID.randomUUID();
        Long timestamp = System.currentTimeMillis();

        this.log("【"+uuid+"】request uri"  +"=> " + httpServletRequest.getRequestURI());

        //获取目标方法的参数信息
        Object[] obj = joinPoint.getArgs();
        for (Object argItem : obj) {
            this.log("【"+uuid+"】request param"  +"=> " +argItem);
        }

        httpServletRequest.setAttribute(UUID_NAME,uuid);
        httpServletRequest.setAttribute(TIMESTAMP_NAME,timestamp);
    }

    /**
     * 后置返回通知
     * 这里需要注意的是:
     * 如果参数中的第一个参数为JoinPoint，则第二个参数为返回值的信息
     * 如果参数中的第一个参数不为JoinPoint，则第一个参数为returning中对应的参数
     * returning 限定了只有目标方法返回值与通知方法相应参数类型时才能执行后置返回通知，否则不执行，对于returning对应的通知方法参数为Object类型将匹配任何目标返回值
     */
    @AfterReturning(value = "autoLogAspect()", returning = "keys")
    public void doAfterReturningAdvice1(JoinPoint joinPoint, Object keys) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes)requestAttributes).getRequest();

        UUID uuid = (UUID) httpServletRequest.getAttribute(UUID_NAME);
        Long startTimestamp = (Long) httpServletRequest.getAttribute(TIMESTAMP_NAME);

        if (keys instanceof Result) {
            Result result = (Result) keys;
            this.log("【"+uuid+"】response param" + "=> " + result );
        }

        Long currentTimestamp = System.currentTimeMillis();
        Long execTimestamp = currentTimestamp - startTimestamp;
        this.log("【"+uuid+"】response exec timemillis"  +"=> " + execTimestamp+" ms");
    }

    /**
     * 后置最终通知（目标方法只要执行完了就会执行后置通知方法）
     */
    @After("autoLogAspect()")
    public void doAfterAdvice(JoinPoint joinPoint) {
    }

    private void log(String content){
//        log.info(content);
        System.out.println(content);
    }


}
