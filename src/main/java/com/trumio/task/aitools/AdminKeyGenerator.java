package com.trumio.task.aitools;

import java.security.SecureRandom;
import java.util.Base64;

public class AdminKeyGenerator {
    public static void main(String[] args) {
        // Length of the key in bytes
        int keyLength = 32; // 32 bytes = 256 bits

        // Secure random generator
        SecureRandom secureRandom = new SecureRandom();

        // Generate random bytes
        byte[] keyBytes = new byte[keyLength];
        secureRandom.nextBytes(keyBytes);

        // Encode as Base64 string (safe for headers)
        String adminKey = Base64.getUrlEncoder().withoutPadding().encodeToString(keyBytes);

        // Print the generated key
        System.out.println("Generated X-Admin-Key: " + adminKey);
    }
}

