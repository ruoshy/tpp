package cn.cwc.web.config;

import cn.cwc.web.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login", "", "/films", "/verify", "/logout", "/index")
                .excludePathPatterns("/css/**", "/img/**", "/js/**")
                .excludePathPatterns("/admin/index", "/admin/film", "/admin/cinema", "/admin/area")
                .excludePathPatterns("/cinema/index", "/cinema/film", "/cinema/arrange", "/cinema/room");
    }
}
