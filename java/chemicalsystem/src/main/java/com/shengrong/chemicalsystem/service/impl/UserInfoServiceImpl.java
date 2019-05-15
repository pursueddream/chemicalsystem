package com.shengrong.chemicalsystem.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.shengrong.chemicalsystem.dao.UserInfoDao;
import com.shengrong.chemicalsystem.model.entity.PersonEntity;
import com.shengrong.chemicalsystem.model.entity.UserInfoEntity;
import com.shengrong.chemicalsystem.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    private final UserInfoDao userInfoDao;

    public UserInfoServiceImpl(UserInfoDao userInfoDao) {
        this.userInfoDao = userInfoDao;
    }

    @Override
    public UserInfoEntity getUserInfoEntityById(String id) {
        return userInfoDao.selectById(id);
    }

    @Override
    public UserInfoEntity getUserInfoEntityByName(String name) {
        UserInfoEntity entity = new UserInfoEntity();
        entity.setUsername(name);
        Wrapper<UserInfoEntity> wrapper = new EntityWrapper<>(entity);
        List<UserInfoEntity> list = userInfoDao.selectList(wrapper);
        return list.get(0);
    }


}
