package com.shengrong.chemicalsystem.utils;

import com.shengrong.chemicalsystem.constant.enums.ExceptionCodeEnum;
import com.shengrong.chemicalsystem.ecxeption.ChemicalException;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

public class RSAUtils {
    private static final String RSA = "RSA";
    private static final int KEY_SIZE = 1024;
    private static final PublicKey PUBLIC_KEY;
    private static final PrivateKey PRIVATE_KEY;
    static {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA);
            keyPairGenerator.initialize(KEY_SIZE);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            PUBLIC_KEY = keyPair.getPublic();
            PRIVATE_KEY = keyPair.getPrivate();
        } catch (Exception e) {
            throw new ChemicalException(ExceptionCodeEnum.CS000);
        }
    }

    public static String encoded (String value) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.ENCRYPT_MODE, PUBLIC_KEY);
        byte[] result = cipher.doFinal(value.getBytes());
        return Base64.encodeBase64String(result);
    }

    public static String decrypt (String value) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, PRIVATE_KEY);
        byte[] bytes = Base64.decodeBase64(value);
        byte[] result = cipher.doFinal(bytes);
        return new String(result);
    }


}
