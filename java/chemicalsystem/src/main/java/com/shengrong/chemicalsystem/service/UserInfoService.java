package com.shengrong.chemicalsystem.service;

import com.shengrong.chemicalsystem.controller.response.PageResult;
import com.shengrong.chemicalsystem.model.entity.UserInfoEntity;
import com.shengrong.chemicalsystem.model.entity.commom.PageEntity;

public interface UserInfoService {
    UserInfoEntity getUserInfoEntityById(String id);
    UserInfoEntity getUserInfoEntityByName(String name);
    void add(UserInfoEntity entity);
    void updateId(UserInfoEntity entity);
    void deleteById(String id);
    PageResult<UserInfoEntity> getPage(UserInfoEntity entity, PageEntity pageEntity);

}
