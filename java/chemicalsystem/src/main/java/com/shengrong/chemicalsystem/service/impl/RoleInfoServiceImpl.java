package com.shengrong.chemicalsystem.service.impl;

import com.shengrong.chemicalsystem.dao.RoleInfoDao;
import com.shengrong.chemicalsystem.model.dto.role.RoleDTO;
import com.shengrong.chemicalsystem.model.entity.RoleInfoEntity;
import com.shengrong.chemicalsystem.service.RoleInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleInfoServiceImpl extends AbstractBaseService<RoleInfoEntity> implements RoleInfoService {

    private final RoleInfoDao roleInfoDao;

    public RoleInfoServiceImpl(RoleInfoDao roleInfoDao) {
        super(roleInfoDao);
        this.roleInfoDao = roleInfoDao;
    }

    @Override
    public List<RoleDTO> getRolesByUserId(String id) {
        return roleInfoDao.getRolesByUserId(id);
    }
}
