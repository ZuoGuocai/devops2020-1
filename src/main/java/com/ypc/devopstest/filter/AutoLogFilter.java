// package com.ypc.devopstest.filter;

// import com.ypc.devopstest.annotation.AutoLog;
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.stereotype.Component;
// import org.springframework.web.method.HandlerMethod;
// import org.springframework.web.servlet.HandlerInterceptor;
// import org.springframework.web.servlet.ModelAndView;

// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
// import java.lang.reflect.Method;
// import java.util.Map;
// import java.util.UUID;

// @Slf4j
// public class AutoLogFilter implements HandlerInterceptor {

//     private static final String  UUID_NAME = "uuid-request";
//     private static final String  TIMESTAMP_NAME = "timestamp-request";

//     @Override
//     public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object){
//         // 如果不是映射到方法直接通过
//         if(!(object instanceof HandlerMethod)){
//             return true;
//         }
//         HandlerMethod handlerMethod=(HandlerMethod)object;
//         Method method=handlerMethod.getMethod();

//         // 日志记录
//         if ( method.isAnnotationPresent(AutoLog.class) ) {
//             UUID uuid = UUID.randomUUID();
//             Long timestamp = System.currentTimeMillis();
//             Map<String, String[]> parameterMap = httpServletRequest.getParameterMap();
//             this.log("【"+uuid+"】request uri"  +"=> " + httpServletRequest.getRequestURI());
//             parameterMap.forEach((k,v)->{
//                 this.log("【"+uuid+"】request param"  +"=> " +k +"="+v[0]);
//             });
//             httpServletRequest.setAttribute(UUID_NAME,uuid);
//             httpServletRequest.setAttribute(TIMESTAMP_NAME,timestamp);
//         }

//         return true;
//     }

//     @Override
//     public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

//         if(!(o instanceof HandlerMethod)){
//             return;
//         }

//         HandlerMethod handlerMethod=(HandlerMethod)o;
//         Method method=handlerMethod.getMethod();

//         // 日志记录
//         if ( method.isAnnotationPresent(AutoLog.class) ) {

//             UUID uuid = (UUID) httpServletRequest.getAttribute(UUID_NAME);
//             Long startTimestamp = (Long) httpServletRequest.getAttribute(TIMESTAMP_NAME);
//             // todo 未获取到返回数据
//             this.log("【"+uuid+"】response json"  +"=> " + httpServletRequest.getAttribute("response_json") );

//             Long currentTimestamp = System.currentTimeMillis();
//             Long execTimestamp = currentTimestamp - startTimestamp;
//             this.log("【"+uuid+"】response exec timemillis"  +"=> " + execTimestamp+" ms");
//         }

//     }
//     @Override
//     public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

//     }

//     private void log(String content){

// //        log.info(content);
//         System.out.println(content);
//     }

// }
