package com.flab.todaymeetup.member.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Member {

    private Long id;
    private String email;
    private String name;
    private String password;
    private String phone;
}
