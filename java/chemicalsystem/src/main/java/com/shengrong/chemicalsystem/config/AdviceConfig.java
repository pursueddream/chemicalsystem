package com.shengrong.chemicalsystem.config;

import com.shengrong.chemicalsystem.constant.CommonConstant;
import com.shengrong.chemicalsystem.ecxeption.ExceptionCodeEnum;
import com.shengrong.chemicalsystem.ecxeption.ChemicalException;
import com.shengrong.chemicalsystem.controller.response.common.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;

@ControllerAdvice
@Slf4j
public class AdviceConfig {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public CommonResponse processUnauthenticatedException(NativeWebRequest request, Exception e) {
        CommonResponse dto = new CommonResponse();
        dto.setFlowId(MDC.get(CommonConstant.FLOW_ID));
        log.error("advice error:", e);
        //内部异常
        if (e instanceof ChemicalException) {
            ExceptionCodeEnum codeEnum = ((ChemicalException) e).getExceptionCodeEnum();
            dto.setCode(codeEnum.getCode());
            dto.setDesc(codeEnum.getDesc());
            return dto;
        }
        //未知异常
        dto.setCode(ExceptionCodeEnum.CS000.getCode());
        dto.setDesc(ExceptionCodeEnum.CS000.getDesc());
        return dto;
    }
}
