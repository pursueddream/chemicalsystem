package com.shengrong.chemicalsystem.service;

import com.shengrong.chemicalsystem.model.entity.UserInfoEntity;

public interface UserInfoService extends BaseService<UserInfoEntity>{
    UserInfoEntity getUserInfoEntityByName(String name);
}
