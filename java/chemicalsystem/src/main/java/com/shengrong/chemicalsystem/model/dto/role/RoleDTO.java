package com.shengrong.chemicalsystem.model.dto.role;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class RoleDTO {
    //角色id
    private String id;
    //角色名称
    private String name;
    //角色权限
    private List<PermissionDTO> permissions;
}
