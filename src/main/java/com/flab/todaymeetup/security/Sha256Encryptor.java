package com.flab.todaymeetup.security;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;

@Component
public class Sha256Encryptor implements Encryptor {

    private static final String SHA_256 = "SHA-256";

    @Override
    public String encrypt(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance(SHA_256);
            byte[] passBytes = password.getBytes();
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

    @Override
    public boolean isMatch(String inputPassword, String memberPassword) {
        return encrypt(inputPassword).equals(memberPassword);
    }
}
