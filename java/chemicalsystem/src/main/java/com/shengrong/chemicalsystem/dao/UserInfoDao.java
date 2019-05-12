package com.shengrong.chemicalsystem.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.shengrong.chemicalsystem.model.entity.UserInfoEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoDao extends BaseMapper<UserInfoEntity> {
}
