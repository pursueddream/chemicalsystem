package com.shengrong.chemicalsystem.service.impl;

import com.shengrong.chemicalsystem.dao.UserRoleRelDao;
import com.shengrong.chemicalsystem.model.entity.UserRoleRelEntity;
import com.shengrong.chemicalsystem.service.UserRoleRelService;
import org.springframework.stereotype.Service;

@Service
public class UserRoleRelServiceImpl extends AbstractBaseService<UserRoleRelEntity> implements UserRoleRelService {
    private final UserRoleRelDao userRoleRelDao;

    public UserRoleRelServiceImpl(UserRoleRelDao userRoleRelDao) {
        super(userRoleRelDao);
        this.userRoleRelDao = userRoleRelDao;
    }
}
