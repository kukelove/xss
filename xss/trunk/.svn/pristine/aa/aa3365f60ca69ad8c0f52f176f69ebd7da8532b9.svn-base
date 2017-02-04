package com.xp.brushms;

//import com.xp.dsp.intercepter.LoginInterceptor;
import com.xp.brushms.intercepter.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Skyline on 2016/6/11.
 */
@Configuration
public class CustomWebMvcConfigurer extends WebMvcConfigurerAdapter {

    @Autowired
    private LoginInterceptor loginInterceptor;


    /**
     * 新增拦截器
     * addPathPatterns 用于添加拦截规则
     * excludePathPatterns 用户排除拦截
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

}
