package com.shengrong.chemicalsystem.security;

import com.alibaba.fastjson.JSON;
import com.shengrong.chemicalsystem.constant.CommonConstant;
import com.shengrong.chemicalsystem.constant.enums.ExceptionCodeEnum;
import com.shengrong.chemicalsystem.model.dto.LoginDTO;
import com.shengrong.chemicalsystem.model.dto.ResponseDTO;
import com.shengrong.chemicalsystem.model.entity.UserInfoEntity;
import com.shengrong.chemicalsystem.service.TokenInfoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
public class CsSecurityHandlerConfig {

    private final TokenInfoService tokenInfoService;

    public CsSecurityHandlerConfig(TokenInfoService tokenInfoService) {
        this.tokenInfoService = tokenInfoService;
    }

    /**
     * 认证成功
     * @return AuthenticationSuccessHandler
     */
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler(){
        return ((request, response, authentication) -> {
            //登录对象
            UserInfoEntity principal = (UserInfoEntity)authentication.getPrincipal();
            //生成token
            tokenInfoService.save(principal.getToken());

            response.setContentType(CommonConstant.JSON_CONTENT_TYPE);
            LoginDTO dto = new LoginDTO();
            dto.setDesc("登录成功");
            dto.setToken("token........test");
            response.getWriter().write(JSON.toJSONString(dto));
            response.getWriter().flush();
        });
    }

    /**
     *  认证失败
     * @return AuthenticationFailureHandler
     */
    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler(){
        return ((request, response, e) -> {
            response.setContentType(CommonConstant.JSON_CONTENT_TYPE);
            ResponseDTO dto = new ResponseDTO();
            dto.setCode(ExceptionCodeEnum.CS003.getCode());
            dto.setDesc(e.getMessage());
            response.getWriter().write(JSON.toJSONString(dto));
            response.getWriter().flush();
        });
    }

    /**
     * 注销
     * @return LogoutSuccessHandler
     */
    @Bean
    public LogoutSuccessHandler logoutSuccessHandler(){
        return ((request, response, authentication) -> {
            String token = CsTokenFilter.getToken(request);
            // 删除token  todo
            ResponseDTO dto = new ResponseDTO();
            dto.setCode("退出成功");
            dto.setDesc("退出成功");
            String jsonString = JSON.toJSONString(dto);
            response.setContentType(CommonConstant.JSON_CONTENT_TYPE);
            response.getWriter().write(jsonString);
            response.getWriter().flush();
        });
    }

    /**
     * 身份验证后权限不足
     * @return AccessDeniedHandler
     */
    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return ((request, response, accessDeniedException) -> {
            ResponseDTO dto = new ResponseDTO();
            dto.setCode("权限不足");
            dto.setDesc("权限不足");
            String jsonString = JSON.toJSONString(dto);
            response.setContentType(CommonConstant.JSON_CONTENT_TYPE);
            response.getWriter().write(jsonString);
            response.getWriter().flush();
        });
    }

    /**
     * 没有进行身份验证访问资源
     * @return AuthenticationEntryPoint
     */
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint(){
        return ((request, response, authException) -> {
            ResponseDTO dto = new ResponseDTO();
            dto.setCode("未进行身份认证");
            dto.setDesc("未进行身份认证");
            String jsonString = JSON.toJSONString(dto);
            response.setContentType(CommonConstant.JSON_CONTENT_TYPE);
            response.getWriter().write(jsonString);
            response.getWriter().flush();
        });
    }


}
