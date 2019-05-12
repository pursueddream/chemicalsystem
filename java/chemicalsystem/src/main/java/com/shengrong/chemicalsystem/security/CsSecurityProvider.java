package com.shengrong.chemicalsystem.security;

import com.shengrong.chemicalsystem.constant.enums.ExceptionCodeEnum;
import com.shengrong.chemicalsystem.ecxeption.ChemicalException;
import com.shengrong.chemicalsystem.model.entity.UserInfoEntity;
import com.shengrong.chemicalsystem.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CsSecurityProvider extends DaoAuthenticationProvider {


    @Autowired
    private UserInfoService userInfoService;

    public CsSecurityProvider(PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        setPasswordEncoder(passwordEncoder);
        setUserDetailsService(userDetailsService);
    }

    @Override
    protected void additionalAuthenticationChecks(
            UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        //数据库密码
        String dbPassword = userDetails.getPassword();
        //前端密码
        String password = authentication.getCredentials().toString();
        if(!dbPassword.equals(password)) {
            throw new BadCredentialsException("账号或者密码错误");
        }


    }
}
