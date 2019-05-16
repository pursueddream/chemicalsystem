package com.shengrong.chemicalsystem.component.impl;

import com.shengrong.chemicalsystem.component.AccessService;
import com.shengrong.chemicalsystem.model.entity.UserInfoEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
@Component
@Slf4j
public class AccessServiceImpl implements AccessService {
    @Override
    public boolean accessAvailable(HttpServletRequest request, Authentication authentication) {

        if(authentication.getPrincipal() instanceof UserInfoEntity){
            String uri = request.getRequestURI();
            return uri.startsWith("/user");
        }

        return false;
    }
}
