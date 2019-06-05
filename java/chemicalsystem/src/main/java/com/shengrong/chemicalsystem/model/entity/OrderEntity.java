package com.shengrong.chemicalsystem.model.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.shengrong.chemicalsystem.model.entity.commom.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@TableName("order_info")
@Getter
@Setter
@ToString
public class OrderEntity  extends BaseEntity {

    //订单名称
    @TableField("name")
    private String name;

    //订单编号
    @TableField("order_no")
    private String orderNo;

    //订单类型
    @TableField("type")
    private String type;

}
