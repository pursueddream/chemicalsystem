package com.shengrong.chemicalsystem.component.impl;

import com.shengrong.chemicalsystem.model.entity.UserInfoEntity;
import com.shengrong.chemicalsystem.service.UserInfoService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CsUserDetailService implements UserDetailsService {

    private final UserInfoService userInfoService;

    public CsUserDetailService(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @Override
    public UserInfoEntity loadUserByUsername(String username) throws UsernameNotFoundException {
        return userInfoService.getUserInfoEntityByName(username);
    }



}
