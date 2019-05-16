package com.shengrong.chemicalsystem.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.shengrong.chemicalsystem.controller.response.PageResult;
import com.shengrong.chemicalsystem.dao.UserInfoDao;
import com.shengrong.chemicalsystem.model.entity.UserInfoEntity;
import com.shengrong.chemicalsystem.model.entity.commom.PageEntity;
import com.shengrong.chemicalsystem.service.UserInfoService;
import com.shengrong.chemicalsystem.utils.DateUtils;
import com.shengrong.chemicalsystem.utils.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    private final UserInfoDao userInfoDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserInfoServiceImpl(UserInfoDao userInfoDao,
                               BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userInfoDao = userInfoDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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

    @Override
    public void add(UserInfoEntity entity) {
        entity.setId(IdUtils.getUUID());
        entity.setCreateTime(DateUtils.getCurrentTime());
        entity.setModifyTime(DateUtils.getCurrentTime());
        //密码加密
        entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
        userInfoDao.insert(entity);
    }

    @Override
    public void updateId(UserInfoEntity entity) {
        entity.setModifyTime(DateUtils.getCurrentTime());
        userInfoDao.updateById(entity);
    }

    @Override
    public void deleteById(String id) {
        userInfoDao.deleteById(id);
    }

    @Override
    public PageResult<UserInfoEntity> getPage(UserInfoEntity entity, PageEntity pageEntity) {
        Wrapper<UserInfoEntity> wrapper = new EntityWrapper<>(entity);
        //查询data
        Page<UserInfoEntity> page = new Page<>(pageEntity.getPageNumber(), pageEntity.getPageSize());
        List<UserInfoEntity> list = userInfoDao.selectPage(page, wrapper);
        //查询total
        int total = userInfoDao.selectCount(wrapper);
        //返回包装
        PageResult<UserInfoEntity> result = new PageResult<>();
        result.setData(list);
        result.setTotal(total);
        return result;
    }


}
