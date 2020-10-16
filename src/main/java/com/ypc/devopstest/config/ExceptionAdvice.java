package com.ypc.devopstest.config;

import com.ypc.devopstest.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@ControllerAdvice
public class ExceptionAdvice implements ResponseBodyAdvice<Object> {


    @Value("${spring.profiles.active}")
    private String profile;

    /**
     * 全局捕获异常
     */
    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    public Result exceptionHandler(HttpServletRequest request,Exception e)
    {
        StackTraceElement[] steArr = e.getStackTrace();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < steArr.length; i++) {
            StackTraceElement ste = steArr[i];
            sb.append(ste.toString()+"----");
        }
        log.error(sb.toString());
        this.sendDingDingTextMessage(request,sb.toString());
        e.printStackTrace();
        return Result.error(Code.EXCEPTION_CODE, "服务器异常");
    }

    /**
     * 方法不对的异常
     */
    @ResponseBody
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public Result exceptionMethodObject(HttpServletRequest req,HttpRequestMethodNotSupportedException exception) {
        return Result.error(Code.EXCEPTION_CODE, "请求Method错误");
    }

    /**
     * 处理请求对象属性不满足校验规则的异常信息
     */
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result exceptionObject(HttpServletRequest request, MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();
        final List<FieldError> fieldErrors = result.getFieldErrors();
        StringBuilder builder = new StringBuilder();

        for (FieldError error : fieldErrors) {
            builder.append(error.getDefaultMessage() + "\n");
        }
        log.error(builder.toString());
        this.sendDingDingTextMessage(request,builder.toString());
        return Result.error(Code.EXCEPTION_CODE, "服务器异常:exceptionObject");
    }

    /**
     * 处理请求单个参数不满足校验规则的异常信息
     */
    @ResponseBody
    @ExceptionHandler(value = ConstraintViolationException.class)
    public Result constraintViolationExceptionHandler(HttpServletRequest request, ConstraintViolationException e) {
        StackTraceElement[] steArr = e.getStackTrace();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < steArr.length; i++) {
            StackTraceElement ste = steArr[i];
            sb.append(ste.toString()+"----");
        }
        log.error(sb.toString());
        this.sendDingDingTextMessage(request,sb.toString());
        return Result.error(Code.EXCEPTION_CODE, "服务器异常:constraintViolation");
    }

    /**
     * 处理请求单个参数不满足校验规则的异常信息
     */
    @ResponseBody
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public Result missingServletRequestParameterExceptionHandler(HttpServletRequest request, MissingServletRequestParameterException e) {
        StackTraceElement[] steArr = e.getStackTrace();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < steArr.length; i++) {
            StackTraceElement ste = steArr[i];
            sb.append(ste.toString()+"----");
        }
        log.error(sb.toString());
        this.sendDingDingTextMessage(request,sb.toString());
        return Result.error(Code.EXCEPTION_CODE, "服务器异常:missingServletRequest");
    }


    /**
     * 这个方法表示对于哪些请求要执行beforeBodyWrite，返回true执行，返回false不执行
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass)
    {
        return true;
    }

    /**
     * 对于返回的对象如果不是最终对象ResponseResult，则选包装一下
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse)
    {



        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if(httpServletRequest.getAttribute(ResponseStatus.class.getSimpleName()) != null)
        {
            ResponseStatus errorType = (ResponseStatus)httpServletRequest.getAttribute(ResponseStatus.class.getSimpleName());
            return Result.error(errorType.code().value(),errorType.reason());
        }

        //如果是字符类型,输出json字符串
        if(mediaType.includes(MediaType.TEXT_HTML)||mediaType.includes(MediaType.TEXT_PLAIN))
        {
            return body;
            //return Result.ok(body);
        }

        //如果已经被异常捕获,返回的就是 正常 对象,不用再次封装了
        if (body instanceof Result<?>) {
            return body;
        }

        //将业务异常捕捉信息做为 第一层展示
        if( body instanceof HashMap){
            HashMap hashMap = (HashMap)body;
            Object statusObject = hashMap.get("status");
            Object messageObject = hashMap.get("message");
            Integer code = Integer.valueOf(statusObject.toString());
            String message = messageObject.toString();
            this.sendDingDingTextMessage(httpServletRequest,messageObject.toString());
            return Result.error(code,message);
        }

        return Result.ok(body);
    }

    /**
     * 钉钉机器人
     * 文本信息
     */
    public void sendDingDingTextMessage(HttpServletRequest httpServletRequest,String content) throws UnsupportedOperationException{

    }


}
