package com.shengrong.chemicalsystem.service;

import com.shengrong.chemicalsystem.model.entity.UserInfoEntity;

public interface UserInfoService {
    UserInfoEntity getUserInfoEntityByToken(String token);
    UserInfoEntity getUserInfoEntityByUsername(String username);
}
