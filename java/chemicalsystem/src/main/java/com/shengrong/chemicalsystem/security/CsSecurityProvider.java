package com.shengrong.chemicalsystem.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class CsSecurityProvider extends DaoAuthenticationProvider {


    public CsSecurityProvider(UserDetailsService userDetailsService) {
        setPasswordEncoder(new BCryptPasswordEncoder());
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
