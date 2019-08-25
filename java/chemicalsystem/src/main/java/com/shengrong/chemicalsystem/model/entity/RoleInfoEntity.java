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
@TableName("role_info")
public class RoleInfoEntity extends BaseEntity {
    @TableField("name")
    private String name;
}
