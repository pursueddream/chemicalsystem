package com.shengrong.chemicalsystem.utils;

import com.shengrong.chemicalsystem.model.entity.UserInfoEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
    public static String getUsername(){
        UserInfoEntity userEntity = getUserEntity();
        return userEntity == null ? "" : userEntity.getUsername();
    }

    public static String getUserId(){
        UserInfoEntity userEntity = getUserEntity();
        return userEntity == null ? "" : userEntity.getId();
    }

    public static UserInfoEntity getUserEntity(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserInfoEntity) {
            return (UserInfoEntity)principal;
        }
        return null;
    }
}
