package com.shengrong.chemicalsystem.model.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@TableName("token_info")
@Getter
@Setter
@ToString
public class TokenInfoEntity extends BaseEntity {
    @TableField("token")
    private String token;
}
