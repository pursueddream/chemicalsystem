package com.shengrong.chemicalsystem.service;

import com.shengrong.chemicalsystem.model.entity.UserInfoEntity;

public interface UserInfoService {
    UserInfoEntity getUserInfoEntityById(String id);
    UserInfoEntity getUserInfoEntityByName(String name);
}
