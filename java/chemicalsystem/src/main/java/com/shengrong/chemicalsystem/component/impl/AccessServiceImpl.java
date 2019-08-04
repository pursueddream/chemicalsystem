package com.shengrong.chemicalsystem.component.impl;

import com.shengrong.chemicalsystem.component.AccessService;
import com.shengrong.chemicalsystem.model.dto.role.PermissionDTO;
import com.shengrong.chemicalsystem.model.dto.role.RoleDTO;
import com.shengrong.chemicalsystem.model.entity.UserInfoEntity;
import com.shengrong.chemicalsystem.service.RoleInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class AccessServiceImpl implements AccessService {
    private final String METHOD = "method";
    private final String RESOURCE = "resource";
    private String  PREFIX = "/api/v1";
    @Autowired
    private RoleInfoService roleInfoService;
    @Override
    public boolean accessAvailable(HttpServletRequest request, Authentication authentication) {
        // test dev need
        String uri = request.getRequestURI();
        log.info("==========================================");
        log.info("request.getRequestURL()={}",request.getRequestURL());
        log.info("request.getRequestURI()={}",request.getRequestURI());
        log.info("request.getServletPath()={}",request.getServletPath());
        log.info("==========================================");
        //不需要进行验证权限
        if (uri.startsWith(PREFIX + "/system")) {
            return true;
        }
        Object principal = authentication.getPrincipal();
        if(principal instanceof UserInfoEntity){
            UserInfoEntity userInfoEntity = (UserInfoEntity)principal;
            //角色列表
            List<RoleDTO> roles = roleInfoService.getRolesByUserId(userInfoEntity.getId());
            //权限
            List<Map<String, String>> permissionMap = getPermissionByRoles(roles);
            log.info("username={} permission={}",userInfoEntity.getUsername(), permissionMap);
            return checkPermission(request.getMethod() , uri, permissionMap);
        }
        return false;
    }

    private boolean checkPermission(String method, String uri, List<Map<String, String>> permissionMap) {
        AntPathMatcher matcher = new AntPathMatcher();
        for (Map<String, String> map : permissionMap) {
            if (map.get(METHOD).equals(method) && matcher.match(map.get(RESOURCE), uri)) {
                return true;
            }
        }
        return false;
    }

    private List<Map<String, String>> getPermissionByRoles(List<RoleDTO> roles) {
        List<Map<String, String>> result = new ArrayList<>();
        for (RoleDTO role : roles) {
            for (PermissionDTO permission : role.getPermissions()) {
                Map<String, String> map = new HashMap<>();
                map.put(METHOD, permission.getMethod());
                map.put(RESOURCE, PREFIX + permission.getResource());
                result.add(map);
            }
        }
        return result;
    }
}
