package com.shengrong.chemicalsystem.service.impl;

import com.shengrong.chemicalsystem.dao.PermissionInfoDao;
import com.shengrong.chemicalsystem.model.entity.PermissionInfoEntity;
import com.shengrong.chemicalsystem.service.PermissionInfoService;
import org.springframework.stereotype.Service;

@Service
public class PermissionInfoServiceImpl extends AbstractBaseService<PermissionInfoEntity> implements PermissionInfoService {

    private final PermissionInfoDao permissionInfoDao;

    public PermissionInfoServiceImpl(PermissionInfoDao permissionInfoDao) {
        super(permissionInfoDao);
        this.permissionInfoDao = permissionInfoDao;
    }
}
