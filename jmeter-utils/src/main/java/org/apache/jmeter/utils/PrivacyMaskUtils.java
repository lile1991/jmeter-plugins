package org.apache.jmeter.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public class PrivacyMaskUtils {
    public static Key createKey(String aesKey) {
        return new SecretKeySpec(Base64.decodeBase64(aesKey), "AES");
    }

    public static String encryptPrivacy(Key key, String unencrypted) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] result = cipher.doFinal(unencrypted.getBytes());
            return Base64.encodeBase64String(result);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static String decryptPrivacy(Key key, String encrypted) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] result = cipher.doFinal(Base64.decodeBase64(encrypted));
            return new String(result);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
