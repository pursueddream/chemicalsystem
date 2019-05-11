package com.shengrong.chemicalsystem.constant.enums;

import lombok.Getter;

@Getter
public enum  ExceptionCodeEnum {

    CS000("CS000", "核心异常"),
    CS001("CS001", "参数错误")
    ;

    private String code;
    private String desc;

    ExceptionCodeEnum (String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
