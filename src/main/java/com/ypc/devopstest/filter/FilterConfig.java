// package com.ypc.devopstest.filter;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// @Configuration
// public class FilterConfig implements WebMvcConfigurer {

//     @Override
//     public void addInterceptors(InterceptorRegistry registry) {
//         registry.addInterceptor(autoLogFilter()).addPathPatterns("/**");
//     }

//     @Bean
//     public AutoLogFilter autoLogFilter() {
//         return new AutoLogFilter();
//     }

// }
