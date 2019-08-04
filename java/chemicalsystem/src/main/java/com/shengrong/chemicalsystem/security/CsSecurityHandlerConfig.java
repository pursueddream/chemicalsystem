package com.shengrong.chemicalsystem.security;

import com.alibaba.fastjson.JSON;
import com.shengrong.chemicalsystem.constant.CommonConstant;
import com.shengrong.chemicalsystem.ecxeption.ExceptionCodeEnum;
import com.shengrong.chemicalsystem.model.dto.LoginDTO;
import com.shengrong.chemicalsystem.controller.response.common.CommonResponse;
import com.shengrong.chemicalsystem.model.entity.UserInfoEntity;
import com.shengrong.chemicalsystem.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Configuration
public class CsSecurityHandlerConfig {


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
            response.setContentType(CommonConstant.JSON_CONTENT_TYPE);
            LoginDTO dto = new LoginDTO();
            String token = TokenUtils.createToken(principal.getId());
            log.info("token={}", token);
            dto.setToken(token);
            dto.setCode("success");
            dto.setDesc("登录成功");
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
            CommonResponse dto = new CommonResponse();
            dto.setCode(ExceptionCodeEnum.CS003.getCode());
            dto.setDesc(e.getMessage());
            response.setStatus(200);
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

            // 删除token  todo
            CommonResponse dto = new CommonResponse();
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
            CommonResponse dto = new CommonResponse();
            dto.setCode("NOT_PERMISSION");
            dto.setDesc("权限不足");
            setResponse(response, dto);
        });
    }

    /**
     * 设置前端相应
     * @param response response
     * @param dto dto
     * @throws IOException IOException
     */
    private void setResponse(HttpServletResponse response, CommonResponse dto) throws IOException {
        dto.setFlowId(MDC.get(CommonConstant.FLOW_ID));
        String jsonString = JSON.toJSONString(dto);
        response.setStatus(200);
        response.setContentType(CommonConstant.JSON_CONTENT_TYPE);
        response.getWriter().write(jsonString);
        response.getWriter().flush();
    }

    /**
     * 没有进行身份验证访问资源
     * @return AuthenticationEntryPoint
     */
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint(){
        return ((request, response, authException) -> {
            CommonResponse dto = new CommonResponse();
            dto.setCode("NOT_LOGIN");
            dto.setDesc("未登录");
            setResponse(response, dto);
        });
    }


}
