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
@TableName("user_role_rel")
public class UserRoleRelEntity extends BaseEntity {
    @TableField("user_id")
    private String userId;
    @TableField("role_id")
    private String roleId;
}
