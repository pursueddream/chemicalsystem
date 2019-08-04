package com.shengrong.chemicalsystem.constant.enums;

import lombok.Getter;

@Getter
public enum MethodTypeEnum {
    GET("GET","GET"),
    POST("POST","POST"),
    PUT("PUT","PUT"),
    DELETE("DELETE","DELETE")
    ;

    private String code;
    private String desc;
    MethodTypeEnum(String code, String desc){
        this.code = code;
        this.desc = desc;
    }

}
