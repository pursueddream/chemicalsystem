package com.shengrong.chemicalsystem.service.impl;

import com.shengrong.chemicalsystem.dao.TokenInfoDao;
import com.shengrong.chemicalsystem.model.entity.TokenInfoEntity;
import com.shengrong.chemicalsystem.service.TokenInfoService;
import com.shengrong.chemicalsystem.utils.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenInfoServiceImpl implements TokenInfoService {

    @Autowired
    private TokenInfoDao tokenInfoDao;

    @Override
    public void save(String token) {
        TokenInfoEntity entity = new TokenInfoEntity();
        entity.setId(IdUtils.getUUID());
        entity.setToken(token);
        tokenInfoDao.insert(entity);
    }
}
