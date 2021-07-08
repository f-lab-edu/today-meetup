package com.flab.todaymeetup.member.controller;

import com.flab.todaymeetup.member.dto.LoginRequestDto;
import com.flab.todaymeetup.member.dto.MemberSignUpRequestDto;
import com.flab.todaymeetup.member.service.LoginService;
import com.flab.todaymeetup.member.service.MemberService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final LoginService loginService;

    @PostMapping
    public void signUp(@Valid @RequestBody MemberSignUpRequestDto memberSignUpRequestDto) {
        memberService.signUp(memberSignUpRequestDto);
    }

    @PostMapping("/login")
    public void login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        loginService.login(loginRequestDto);
    }
}
