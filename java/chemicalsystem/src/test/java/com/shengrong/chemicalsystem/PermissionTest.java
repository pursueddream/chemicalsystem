package com.shengrong.chemicalsystem;

import com.shengrong.chemicalsystem.constant.enums.MethodTypeEnum;
import com.shengrong.chemicalsystem.model.entity.PermissionInfoEntity;
import com.shengrong.chemicalsystem.service.PermissionInfoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PermissionTest {

    @Autowired
    private PermissionInfoService service;

    @Test
    public void add(){
        PermissionInfoEntity entity = new PermissionInfoEntity();

        entity.setResource("/order/list");
        entity.setMethod(MethodTypeEnum.GET.getCode());
        entity.setName("查询订单详情");
        service.insert(entity);

        entity.setResource("/order/detail");
        entity.setMethod(MethodTypeEnum.GET.getCode());
        entity.setName("查询订单详情");
        service.insert(entity);

        entity.setResource("/order");
        entity.setMethod(MethodTypeEnum.POST.getCode());
        entity.setName("创建订单");
        service.insert(entity);

        entity.setResource("/order");
        entity.setMethod(MethodTypeEnum.PUT.getCode());
        entity.setName("修改订单信息");
        service.insert(entity);

        entity.setResource("/order");
        entity.setMethod(MethodTypeEnum.DELETE.getCode());
        entity.setName("删除用户");
        service.insert(entity);
    }
}
