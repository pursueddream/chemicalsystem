package com.shengrong.chemicalsystem;

import com.shengrong.chemicalsystem.dao.RoleInfoDao;
import com.shengrong.chemicalsystem.model.dto.role.RoleDTO;
import com.shengrong.chemicalsystem.model.entity.RoleInfoEntity;
import com.shengrong.chemicalsystem.service.RoleInfoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RoleTest {


    @Autowired
    private RoleInfoDao dao;

    @Autowired
    private RoleInfoService service;

    @Test
    public void add(){
        RoleInfoEntity entity = new RoleInfoEntity();
        entity.setName("订单管理员");
        service.insert(entity);
    }

    @Test
    public void select(){
        List<RoleDTO> list = dao.getRolesByUserId("d55235dcc673480c94ae76b82ba1c124");
        log.info(list.toString());
    }
}
