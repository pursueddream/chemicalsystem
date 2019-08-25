package com.shengrong.chemicalsystem.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class LoginDTO {

    private String code;
    private String desc;
    private String token;

}
