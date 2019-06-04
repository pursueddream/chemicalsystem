package com.shengrong.chemicalsystem.component.impl;

import com.shengrong.chemicalsystem.component.AccessService;
import com.shengrong.chemicalsystem.model.entity.UserInfoEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class AccessServiceImpl implements AccessService {
    @Override
    public boolean accessAvailable(HttpServletRequest request, Authentication authentication) {
        String uri = request.getRequestURI();
        if (uri.startsWith("/system")) {
            return true;
        }
        List<String> roles = authentication.getAuthorities().stream().map( x -> ((GrantedAuthority) x).getAuthority()).collect(Collectors.toList());
        //处理admin
        if (roles.contains("admin")) {
            return true;
        }
        //其他请求分别进行处理
        if(authentication.getPrincipal() instanceof UserInfoEntity){
            return uri.startsWith("/user");
        }

        return false;
    }
}
