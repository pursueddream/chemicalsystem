package com.shengrong.chemicalsystem.service.impl;

import com.shengrong.chemicalsystem.dao.RolePermissionRelDao;
import com.shengrong.chemicalsystem.model.entity.RolePermissionRelEntity;
import com.shengrong.chemicalsystem.service.RolePermissionRelService;
import org.springframework.stereotype.Service;

@Service
public class RolePermissionRelServiceImpl extends AbstractBaseService<RolePermissionRelEntity> implements RolePermissionRelService {

    private final RolePermissionRelDao rolePermissionRelDao;

    public RolePermissionRelServiceImpl(RolePermissionRelDao rolePermissionRelDao) {
        super(rolePermissionRelDao);
        this.rolePermissionRelDao = rolePermissionRelDao;
        }
}
