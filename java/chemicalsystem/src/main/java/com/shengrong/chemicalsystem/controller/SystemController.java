package com.shengrong.chemicalsystem.controller;

import com.shengrong.chemicalsystem.utils.RSAUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

@RestController
public class SystemController {

    private static final String EXPONENT = "exponent";
    private static final String MODULUS = "modulus";
    private static final int DEF_RADIX = 16;

    @RequestMapping(method = RequestMethod.GET, value = "/system/publicKey")
    public Map<String, String> getPublicKey(){
        PublicKey publicKey = RSAUtils.getPublicKey();
        RSAPublicKey rsaPublicKey = (RSAPublicKey) publicKey;
        String exponent = rsaPublicKey.getPublicExponent().toString(DEF_RADIX);
        String modulus = rsaPublicKey.getModulus().toString(DEF_RADIX);
        Map<String, String> map = new HashMap<>();
        map.put(EXPONENT, exponent);
        map.put(MODULUS, modulus);
        return map;
    }

}
