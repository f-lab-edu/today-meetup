package com.flab.todaymeetup.util.security;

import java.security.MessageDigest;

public class EncryptUtil {

    private static final String SHA_256 = "SHA-256";

    public static String encryptSha256(String s) {
        try {
            MessageDigest md = MessageDigest.getInstance(SHA_256);
            byte[] passBytes = s.getBytes();
            md.reset();
            byte[] digested = md.digest(passBytes);
            StringBuilder sb = new StringBuilder();
            for (byte b :digested) {
                sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            }

            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("암호화 수행 중 문제가 발생하였습니다.", e);
        }
    }
}
