package com.shengrong.chemicalsystem.utils;

import com.shengrong.chemicalsystem.constant.CommonConstant;
import com.shengrong.chemicalsystem.controller.response.common.CommonResponse;
import org.slf4j.MDC;

public class ResponseUtils {

    public static CommonResponse getDefResponse(){
        CommonResponse dto = new CommonResponse();
        dto.setCode(CommonConstant.SUCCESS);
        dto.setDesc(CommonConstant.SUCCESS);
        dto.setFlowId(MDC.get(CommonConstant.FLOW_ID));
        return dto;
    }

    public static CommonResponse getDataResponse(Object data){
        CommonResponse response = getDefResponse();
        response.setResult(data);
        return response;
    }
}
