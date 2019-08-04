package com.shengrong.chemicalsystem.config;

import com.shengrong.chemicalsystem.interceptor.FlowIdInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class WebConfig  implements WebMvcConfigurer {

    private final FlowIdInterceptor interceptor;

    public WebConfig(FlowIdInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor).addPathPatterns("/**");
    }
}
