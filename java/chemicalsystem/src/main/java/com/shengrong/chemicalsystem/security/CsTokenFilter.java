package com.shengrong.chemicalsystem.security;

import com.shengrong.chemicalsystem.constant.CommonConstant;
import com.shengrong.chemicalsystem.model.entity.UserInfoEntity;
import com.shengrong.chemicalsystem.service.UserInfoService;
import com.shengrong.chemicalsystem.utils.TokenUtils;
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

import static com.shengrong.chemicalsystem.utils.TokenUtils.getIdByToken;

@Component
@Slf4j
public class CsTokenFilter extends OncePerRequestFilter {

    private final UserInfoService userInfoServiceImpl;

    public CsTokenFilter(UserInfoService userInfoServiceImpl) {
        this.userInfoServiceImpl = userInfoServiceImpl;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String token = TokenUtils.getToken(request);
        log.info("token={}", token);
        //认证
        if (!StringUtils.isEmpty(token)) {
            //解密token
            String userInfoId = getIdByToken(token);
            UserInfoEntity userInfoEntity = userInfoServiceImpl.queryById(userInfoId);
            UsernamePasswordAuthenticationToken authentication
                    = new UsernamePasswordAuthenticationToken(userInfoEntity, null, userInfoEntity.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        //放行
        chain.doFilter(request, response);
    }


}
