package com.shengrong.chemicalsystem.model.dto.role;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class PermissionDTO {
    private String id;
    private String name;
    private String resource;
    private String method;
}
