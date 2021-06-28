package com.flab.todaymeetup.security;

public interface Encryptor {

    String encrypt(String password);

    boolean isMatch(String inputPassword, String memberPassword);
}
