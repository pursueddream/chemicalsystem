package com.shengrong.chemicalsystem.service;

import com.shengrong.chemicalsystem.model.dto.role.RoleDTO;
import com.shengrong.chemicalsystem.model.entity.RoleInfoEntity;

import java.util.List;

public interface RoleInfoService extends BaseService<RoleInfoEntity> {
    List<RoleDTO> getRolesByUserId(String id);
}
