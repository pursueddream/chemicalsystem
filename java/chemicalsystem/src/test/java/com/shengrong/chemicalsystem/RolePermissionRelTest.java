package com.shengrong.chemicalsystem;

import com.shengrong.chemicalsystem.model.entity.RolePermissionRelEntity;
import com.shengrong.chemicalsystem.service.RolePermissionRelService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RolePermissionRelTest {
    @Autowired
    private RolePermissionRelService service;
    @Test
    public void add(){
        RolePermissionRelEntity entity = new RolePermissionRelEntity();
        entity.setRoleId("c9ffc6fa67164363b3e1871993e9ec0a");

        entity.setPermissionId("114fca14f7774a40976c027a6e6199c0");
        service.insert(entity);

        entity.setPermissionId("6351118e17534291a87101ab54a51214");
        service.insert(entity);

        entity.setPermissionId("b378eb53c01a469c8d231802abf15a48");
        service.insert(entity);

        entity.setPermissionId("d492e4869ccc4fbfa56aadcad0726e0f");
        service.insert(entity);

        entity.setPermissionId("edd1a9ed6de54f91872db57711204f40");
        service.insert(entity);
    }
}
