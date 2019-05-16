package com.shengrong.chemicalsystem.controller.request;

import com.shengrong.chemicalsystem.controller.request.common.PageRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserInfoRequest extends PageRequest {

    private String username;

}
