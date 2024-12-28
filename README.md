# Time-Based OTP Generator for Android

This Java implementation demonstrates how to create a Time-Based One-Time Password (TOTP) mechanism within an Android application. TOTPs add an extra layer of security to API communication, mitigating risks such as credential theft or unauthorized access.

---

## Features
- **Dynamic OTP Generation**: Based on the current timestamp.
- **Enhanced Security**: Prevents API misuse and unauthorized access.
- **Simple Integration**: Easily integrate this functionality into any Android app.

---

## Prerequisites
- Android Studio installed.
- Basic understanding of Java for Android development.
- Familiarity with Android API usage and secure coding practices.

---

## Code Implementation

```java

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
