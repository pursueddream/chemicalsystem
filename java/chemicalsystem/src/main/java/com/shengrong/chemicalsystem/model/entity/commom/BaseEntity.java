package com.shengrong.chemicalsystem.model.entity.commom;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
@Getter
@Setter
@ToString
public class BaseEntity{

    //主键
    @TableId("id")
    private String id;

    //创建时间
    @TableField("create_time")
    private Timestamp createTime;

    //修改时间
    @TableField("modify_time")
    private Timestamp modifyTime;

}
