package com.shengrong.chemicalsystem.aop;

import com.shengrong.chemicalsystem.component.ValidateComponent;
import com.shengrong.chemicalsystem.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

@Slf4j
@Aspect
@Configuration
public class CommonAop {

    private final ValidateComponent validateComponent;

    @Autowired
    public CommonAop(ValidateComponent validateComponent) {
        this.validateComponent = validateComponent;
    }


    @Pointcut("execution(* com.shengrong.chemicalsystem.controller..*.*(..))")
    public void executeLogAop(){
    }

    @Around("executeLogAop()")
    public Object doAround(ProceedingJoinPoint point) throws Throwable{
        //开始时间
        long startTime = System.currentTimeMillis();
        String className = point.getTarget().getClass().getName();
        String methodName = point.getSignature().getName();
        //返回值
        Object response;
        try {
            //入参日志
            paramsLog(point, className, methodName);
            //参数校验
            for (Object param : point.getArgs()) {
                validateComponent.validate(param);
            }
            response = point.proceed();
        } catch (Exception e){
            log.error(className + "." + methodName + "controller异常结束##########################################");
            throw e;
        }
        long endTime = System.currentTimeMillis();
        log.info("完成请求的时间{}", startTime - endTime);
        log.info("response:{}", JsonUtils.toString(response));
        log.info(className + "." + methodName + "controller结束##########################################");
        return response;

    }

    private void paramsLog(ProceedingJoinPoint point, String className, String methodName) {
        //类名 方法名

        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        if (ra != null) {
            ServletRequestAttributes sra = (ServletRequestAttributes) ra;
            HttpServletRequest request = sra.getRequest();
            Enumeration<String> enums = request.getParameterNames();
            List<String> params = new ArrayList<>();
            while (enums.hasMoreElements()) {
                String paramName = enums.nextElement();
                String param = paramName + ":" + request.getParameter(paramName);
                params.add(param);
            }
            log.info(className + "." + methodName + "-controller开始##########################################");
            log.info("请求====>RUL:{},method:{},params:{}, class method:{}, args:{}",
                    request.getRequestURL().toString(),
                    request.getMethod(),
                    JsonUtils.toString(params),
                    className + "." + methodName,
                    point.getArgs());
        }
    }

}
