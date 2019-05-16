package com.shengrong.chemicalsystem.controller;

import com.shengrong.chemicalsystem.controller.request.UserInfoRequest;
import com.shengrong.chemicalsystem.controller.response.PageResult;
import com.shengrong.chemicalsystem.model.entity.UserInfoEntity;
import com.shengrong.chemicalsystem.model.entity.commom.PageEntity;
import com.shengrong.chemicalsystem.service.UserInfoService;
import com.shengrong.chemicalsystem.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserInfoController {

    private final UserInfoService userInfoService;

    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user/list")
    public PageResult<UserInfoEntity> getPage(UserInfoRequest request){

        UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setUsername(request.getUsername());

        PageEntity pageEntity = new PageEntity();
        pageEntity.setPageNumber(request.getPageNumber());
        pageEntity.setPageSize(request.getPageSize());

        return userInfoService.getPage(userInfoEntity, pageEntity);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user/{id}")
    public UserInfoEntity queryById(@PathVariable("id") String id){
        return userInfoService.getUserInfoEntityById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/user")
    public Object add(@RequestBody UserInfoEntity entity){
        userInfoService.add(entity);
        return ResponseUtils.getDefResponse();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/user/{id}")
    public Object modifyById(@PathVariable("id") String id, @RequestBody UserInfoEntity entity){
        userInfoService.updateId(entity);
        return ResponseUtils.getDefResponse();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/user/{id}")
    public Object deleteById(@PathVariable("id") String id){
        userInfoService.deleteById(id);
        return ResponseUtils.getDefResponse();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/xxx")
    public Object xxx(){
        return ResponseUtils.getDefResponse();
    }

}
