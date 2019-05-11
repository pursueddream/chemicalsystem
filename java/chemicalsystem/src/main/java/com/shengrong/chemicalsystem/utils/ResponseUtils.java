package com.shengrong.chemicalsystem.utils;

import com.shengrong.chemicalsystem.constant.CommonConstant;
import com.shengrong.chemicalsystem.model.dto.ResponseDTO;

public class ResponseUtils {
    public static ResponseDTO getDefResponse(){
        ResponseDTO dto = new ResponseDTO();
        dto.setCode(CommonConstant.SUCCESS);
        dto.setDesc(CommonConstant.SUCCESS);
        return dto;
    }

}
