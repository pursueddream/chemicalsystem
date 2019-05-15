package com.shengrong.chemicalsystem.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



@Configuration
@Slf4j
public class CsSecurityProvider extends DaoAuthenticationProvider {

    private final UserDetailsService csUserDetailService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public CsSecurityProvider(UserDetailsService csUserDetailService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.csUserDetailService = csUserDetailService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        setPasswordEncoder(bCryptPasswordEncoder);
        setUserDetailsService(csUserDetailService);
    }

    @Override
    protected void additionalAuthenticationChecks(
            UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        //数据库  密码
        String dbPassword = userDetails.getPassword();

        //前端密码
        String password = authentication.getCredentials().toString();

        if(!dbPassword.equals(password)) {
            throw new BadCredentialsException("账号或者密码错误");
        }


    }
}
