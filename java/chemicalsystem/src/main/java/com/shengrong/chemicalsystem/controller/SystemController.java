package com.shengrong.chemicalsystem.controller;

import com.shengrong.chemicalsystem.utils.RSAUtils;
import org.apache.commons.codec.binary.Base64;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.interfaces.RSAPublicKey;

@RestController
public class SystemController {

    @RequestMapping(method = RequestMethod.GET, value = "/system/publicKey")
    public String getPublicKey(){
        RSAPublicKey publicKey = RSAUtils.getPublicKey();
        byte[] keyEncoded = publicKey.getEncoded();
        return Base64.encodeBase64String(keyEncoded);

    }

}
