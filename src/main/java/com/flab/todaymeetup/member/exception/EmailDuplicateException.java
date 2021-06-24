package com.flab.todaymeetup.member.exception;

public class EmailDuplicateException extends RuntimeException {

    public EmailDuplicateException(String email) {
        super("이미 존재하는 이메일 주소입니다. (email: " + email + ")");
    }
}
