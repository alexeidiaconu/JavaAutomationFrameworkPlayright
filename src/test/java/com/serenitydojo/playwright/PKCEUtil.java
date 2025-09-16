package com.serenitydojo.playwright;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

public class PKCEUtil {

    // Generate a random code verifier
    public static String generateCodeVerifier() {
        byte[] randomBytes = new byte[32];
        new SecureRandom().nextBytes(randomBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
    }

    // Generate code challenge from verifier
    public static String generateCodeChallenge(String codeVerifier) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(codeVerifier.getBytes(StandardCharsets.US_ASCII));
        return Base64.getUrlEncoder().withoutPadding().encodeToString(hash);
    }

    public static void main(String[] args) {

        String codeVerifier = PKCEUtil.generateCodeVerifier();
        System.out.println("code veryfier: " + codeVerifier);
        try {
            String codeChallenge = PKCEUtil.generateCodeChallenge(codeVerifier);
            System.out.println("codeChallenge: " + codeChallenge);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }



    }
}
