package com.shengrong.chemicalsystem.security;

import com.shengrong.chemicalsystem.constant.CommonConstant;
import com.shengrong.chemicalsystem.model.entity.UserInfoEntity;
import com.shengrong.chemicalsystem.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
@Slf4j
public class CsTokenFilter extends OncePerRequestFilter {

    @Autowired
    private UserInfoService userInfoService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String token = getToken(request);
        log.info("token={}", token);
        //认证
        if (!StringUtils.isEmpty(token)) {
            UserInfoEntity userInfo = userInfoService.getUserInfoEntityByToken(token);
            if (userInfo != null) {
                //添加认证
                UsernamePasswordAuthenticationToken authentication
                        = new UsernamePasswordAuthenticationToken(userInfo, null, userInfo.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        //放行
        chain.doFilter(request, response);

    }

    public static String getToken(HttpServletRequest request) {
        return request.getHeader(CommonConstant.AUTHORIZATION);
    }
}
