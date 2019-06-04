package com.shengrong.chemicalsystem.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.shengrong.chemicalsystem.dao.UserInfoDao;
import com.shengrong.chemicalsystem.model.entity.UserInfoEntity;
import com.shengrong.chemicalsystem.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserInfoServiceImpl extends AbstractBaseService<UserInfoEntity> implements UserInfoService {

    private final UserInfoDao userInfoDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserInfoServiceImpl(UserInfoDao userInfoDao,
                               BCryptPasswordEncoder bCryptPasswordEncoder) {
        super(userInfoDao);
        this.userInfoDao = userInfoDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserInfoEntity getUserInfoEntityByName(String name) {
        UserInfoEntity entity = new UserInfoEntity();
        entity.setUsername(name);
        Wrapper<UserInfoEntity> wrapper = new EntityWrapper<>(entity);
        List<UserInfoEntity> list = userInfoDao.selectList(wrapper);
        if (list.isEmpty()) {
            log.error("账号或者密码错误");
            throw new BadCredentialsException("账号或者密码错误");
        }
        return list.get(0);

    }

    @Override
    public String insert(UserInfoEntity entity) {
        //密码需要加密，重写insert方法
        entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
        return super.insert(entity);
    }
}
