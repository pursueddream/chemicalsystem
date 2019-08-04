package com.shengrong.chemicalsystem;

import com.shengrong.chemicalsystem.model.entity.UserRoleRelEntity;
import com.shengrong.chemicalsystem.service.UserRoleRelService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserRoleRelTest {

    @Autowired
    private UserRoleRelService service;
    @Test
    public void add () {
        UserRoleRelEntity entity = new UserRoleRelEntity();
        entity.setUserId("d55235dcc673480c94ae76b82ba1c124");
        entity.setRoleId("c9ffc6fa67164363b3e1871993e9ec0a");
        service.insert(entity);
    }
}
