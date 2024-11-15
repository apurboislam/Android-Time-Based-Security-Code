package com.bd.apstech.timebasedsecuritycode;

import java.nio.charset.StandardCharsets;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class SecurityCodeUtils {
    public static String generateSecurityCode(String secretKey, int length) throws Exception {
        long time = System.currentTimeMillis() / 1000L;

        time /= 30; //different code generate per 30 second

        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        byte[] hash = sha256_HMAC.doFinal(Long.toString(time).getBytes(StandardCharsets.UTF_8));

        String characters = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder code = new StringBuilder();
        for (byte b : hash) {
            int index = (b & 0xFF) % 62;
            code.append(characters.charAt(index));
        }

        return code.substring(0, Math.min(code.length(), length));
    }
}
