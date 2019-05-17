package com.shengrong.chemicalsystem.controller;

import com.shengrong.chemicalsystem.controller.response.PublicKeyResponse;
import com.shengrong.chemicalsystem.utils.RSAUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;

@RestController
public class SystemController {

    private static final int DEF_RADIX = 16;

    @RequestMapping(method = RequestMethod.GET, value = "/system/publicKey")
    public PublicKeyResponse getPublicKey(){
        PublicKey publicKey = RSAUtils.getPublicKey();
        RSAPublicKey rsaPublicKey = (RSAPublicKey) publicKey;
        String exponent = rsaPublicKey.getPublicExponent().toString(DEF_RADIX);
        String modulus = rsaPublicKey.getModulus().toString(DEF_RADIX);
        PublicKeyResponse response = new PublicKeyResponse();
        response.setExponent(exponent);
        response.setModulus(modulus);
        return response;
    }

}
