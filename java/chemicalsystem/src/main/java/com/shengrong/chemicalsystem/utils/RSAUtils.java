package com.shengrong.chemicalsystem.utils;

import com.shengrong.chemicalsystem.ecxeption.ChemicalException;
import com.shengrong.chemicalsystem.ecxeption.ExceptionCodeEnum;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;

public class RSAUtils {
    private static final Provider PROVIDER = new BouncyCastleProvider();
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
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.DECRYPT_MODE, PRIVATE_KEY);
        byte[] bytes = Base64.decodeBase64(value);
        byte[] result = cipher.doFinal(bytes);
        return new String(result);
    }

    public static RSAPublicKey getPublicKey() {
        return (RSAPublicKey)PUBLIC_KEY;
    }


}
