package com.shengrong.chemicalsystem.component;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface AccessService {

    boolean accessAvailable(HttpServletRequest request, Authentication authentication);
}
