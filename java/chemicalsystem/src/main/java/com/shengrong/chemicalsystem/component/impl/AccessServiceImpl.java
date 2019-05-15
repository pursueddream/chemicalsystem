package com.shengrong.chemicalsystem.component.impl;

import com.shengrong.chemicalsystem.component.AccessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
@Component
@Slf4j
public class AccessServiceImpl implements AccessService {
    @Override
    public boolean accessAvailable(HttpServletRequest request) {

        return false;
    }
}
