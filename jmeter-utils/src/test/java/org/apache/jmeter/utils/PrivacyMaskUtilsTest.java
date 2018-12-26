package org.apache.jmeter.utils;

import com.le.jmeter.utils.PrivacyMaskUtils;
import org.apache.commons.codec.binary.Base64;

import java.security.Key;

public class PrivacyMaskUtilsTest {
    public static void main(String[] args) {
        Key key = PrivacyMaskUtils.createKey(Base64.encodeBase64String("1234567890123456".getBytes()));

        String encryptMobile = PrivacyMaskUtils.encryptPrivacy(key, "18888888888");
        System.out.println("加密后的手机号: " + encryptMobile);

        String decryptMobile = PrivacyMaskUtils.decryptPrivacy(key, encryptMobile);
        System.out.println("解密后的手机号: " + decryptMobile);
    }
}
