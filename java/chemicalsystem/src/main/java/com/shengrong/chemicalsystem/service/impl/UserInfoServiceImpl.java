package com.shengrong.chemicalsystem.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.shengrong.chemicalsystem.constant.enums.ExceptionCodeEnum;
import com.shengrong.chemicalsystem.dao.UserInfoDao;
import com.shengrong.chemicalsystem.ecxeption.ChemicalException;
import com.shengrong.chemicalsystem.model.entity.UserInfoEntity;
import com.shengrong.chemicalsystem.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;
    @Override
    public UserInfoEntity getUserInfoEntityByToken(String token) {

        //查询条件
        UserInfoEntity entity = new UserInfoEntity();
        entity.setToken(token);
        return getSingleEntity(entity);
    }

    @Override
    public UserInfoEntity getUserInfoEntityByUsername(String username) {
        //查询条件
        UserInfoEntity entity = new UserInfoEntity();
        entity.setUsername(username);
        return getSingleEntity(entity);
    }

    private UserInfoEntity getSingleEntity(UserInfoEntity entity) {
        Wrapper<UserInfoEntity> wrapper = new EntityWrapper<>(entity);
        //查询
        List<UserInfoEntity> list = userInfoDao.selectList(wrapper);
        if (list == null || list.size() != 1) {
            throw new ChemicalException(ExceptionCodeEnum.CS002);
        }
        return list.get(0);
    }


}
