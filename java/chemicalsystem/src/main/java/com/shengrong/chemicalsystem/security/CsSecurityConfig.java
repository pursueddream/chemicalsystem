package com.shengrong.chemicalsystem.security;

import com.shengrong.chemicalsystem.constant.CommonConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
@EnableWebSecurity
public class CsSecurityConfig extends WebSecurityConfigurerAdapter {

    //登录成功
    private final AuthenticationSuccessHandler authenticationSuccessHandler;
    //登录失败
    private final AuthenticationFailureHandler authenticationFailureHandler;
    //注销成功
    private final LogoutSuccessHandler logoutSuccessHandler;
    //过滤器
    private final CsTokenFilter csTokenFilter;
    //认证
    private final AuthenticationProvider csSecurityProvider;
    //认证后权限不足
    private final AccessDeniedHandler accessDeniedHandler;
    //未认证访问有权限的
    private final AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    public CsSecurityConfig(AuthenticationProvider csSecurityProvider,
                            AuthenticationSuccessHandler authenticationSuccessHandler,
                            AuthenticationFailureHandler authenticationFailureHandler,
                            LogoutSuccessHandler logoutSuccessHandler,
                            CsTokenFilter csTokenFilter,
                            AccessDeniedHandler accessDeniedHandler,
                            AuthenticationEntryPoint authenticationEntryPoint) {
        this.csSecurityProvider = csSecurityProvider;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.authenticationFailureHandler = authenticationFailureHandler;
        this.logoutSuccessHandler = logoutSuccessHandler;
        this.csTokenFilter = csTokenFilter;
        this.accessDeniedHandler = accessDeniedHandler;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(csSecurityProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        //使用JWT
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()
                .antMatchers("/system/**").permitAll()
                .anyRequest().access("@accessServiceImpl.accessAvailable(request, authentication)");

        //登录成功
        http.formLogin()
                .loginProcessingUrl(CommonConstant.LOGIN)//登录路径
                .successHandler(authenticationSuccessHandler)//登录成功
                .failureHandler(authenticationFailureHandler).permitAll();//登录失败

        //注销
        http.logout()
                .logoutUrl(CommonConstant.LOGOUT)//注销路径
                .logoutSuccessHandler(logoutSuccessHandler);//注销成功

        //未认证访问有权限的
        http.httpBasic()
                .authenticationEntryPoint(authenticationEntryPoint);

        //已经认证，没有权限
        http.exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler);

        //JWT过滤器
        http.addFilterBefore(csTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
