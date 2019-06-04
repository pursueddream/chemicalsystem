package com.shengrong.chemicalsystem.ecxeption;

import lombok.Getter;

@Getter
public enum  ExceptionCodeEnum {

    CS000("CS000", "核心异常"),
    CS001("CS001", "参数错误"),
    CS002("CS002", "user不存在或user存在多个"),
    CS003("CS003", "账号或者密码错误"),
    CS004("CS004", "token加密失败"),
    CS005("CS005", "token检验失败"),
    ;

    private String code;
    private String desc;

    ExceptionCodeEnum (String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
