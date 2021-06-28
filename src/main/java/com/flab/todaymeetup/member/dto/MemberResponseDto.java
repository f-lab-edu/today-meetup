package com.flab.todaymeetup.member.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberResponseDto {

    private Long id;
    private String email;
    private String name;
    private String phone;
}
