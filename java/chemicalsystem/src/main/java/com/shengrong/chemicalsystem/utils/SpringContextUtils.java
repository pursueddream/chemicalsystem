package com.shengrong.chemicalsystem.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.EventObject;
@Component
public class SpringContextUtils implements ApplicationContextAware {

    private static ApplicationContext context;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static void publish(EventObject source){
        context.publishEvent(source);
    }

    public static <T> T getBean(Class<T> cla){
        return context.getBean(cla);
    }

}
