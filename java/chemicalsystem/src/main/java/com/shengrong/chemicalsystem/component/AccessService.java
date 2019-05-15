package com.shengrong.chemicalsystem.component;

import javax.servlet.http.HttpServletRequest;

public interface AccessService {

    boolean accessAvailable(HttpServletRequest request);
}
