package com.shengrong.chemicalsystem.config;

import com.shengrong.chemicalsystem.constant.CommonConstant;
import com.shengrong.chemicalsystem.utils.IdUtils;
import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

@EnableAsync
@Configuration
public class ThreadPoolConfig {

    @Bean
    public Executor executor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor(){
            @Override
            public Future<?> submit(Runnable task) {
                return super.submit(wrap(task, MDC.getCopyOfContextMap()));
            }

            @Override
            public void execute(Runnable task) {
                super.execute(wrap(task, MDC.getCopyOfContextMap()));
            }
        };
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(6);
        executor.setThreadNamePrefix("chemical-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }


    private <T> Callable<T> wrap(final Callable<T> callable, final Map<String, String> context) {
        return () -> {
            checkContext(context);
            try {
                return callable.call();
            } finally {
                MDC.clear();
            }
        };
    }

    private Runnable wrap(final Runnable runnable, final Map<String, String> context) {
        return () -> {
            checkContext(context);
            try {
                runnable.run();
            } finally {
                MDC.clear();
            }
        };
    }

    private void checkContext(Map<String, String> context) {
        if (context == null) {
            MDC.clear();
        } else {
            MDC.setContextMap(context);
        }
        if (MDC.get(CommonConstant.FLOW_ID) == null) {
            MDC.put(CommonConstant.FLOW_ID, IdUtils.getUUID());
        }
    }
}
