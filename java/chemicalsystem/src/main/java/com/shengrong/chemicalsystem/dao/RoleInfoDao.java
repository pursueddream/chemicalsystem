package com.shengrong.chemicalsystem.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.shengrong.chemicalsystem.model.dto.role.RoleDTO;
import com.shengrong.chemicalsystem.model.entity.RoleInfoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleInfoDao extends BaseMapper<RoleInfoEntity> {
    List<RoleDTO> getRolesByUserId(String id);
}
