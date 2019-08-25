package com.shengrong.chemicalsystem.model.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.shengrong.chemicalsystem.model.entity.commom.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@TableName("role_permission_rel")
public class RolePermissionRelEntity extends BaseEntity {

    @TableField("role_id")
    private String roleId;

    @TableField("permission_id")
    private String permissionId;
}
