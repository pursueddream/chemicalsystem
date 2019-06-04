package com.shengrong.chemicalsystem.security;

import com.shengrong.chemicalsystem.utils.RSAUtils;
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

        //前端密码(密文)
        String password = authentication.getCredentials().toString();
        //前端解密(铭文)
        String inscriptionPassword;
        try {
            //正式环境中放开
            inscriptionPassword = RSAUtils.decrypt(password);
//            inscriptionPassword = password;
        } catch (Exception e) {
            log.error("账号或者密码错误", e);
            throw new BadCredentialsException("账号或者密码错误");
        }
        //数据库密码与铭文密码进行校验
        if(!bCryptPasswordEncoder.matches(inscriptionPassword, dbPassword)) {
            log.error("账号或者密码错误");
            throw new BadCredentialsException("账号或者密码错误");
        }
    }
}
