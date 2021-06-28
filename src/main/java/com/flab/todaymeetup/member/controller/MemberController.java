package com.flab.todaymeetup.member.controller;

import com.flab.todaymeetup.constant.Constant;
import com.flab.todaymeetup.member.dto.LoginRequestDto;
import com.flab.todaymeetup.member.dto.MemberResponseDto;
import com.flab.todaymeetup.member.dto.MemberSignUpRequestDto;
import com.flab.todaymeetup.member.service.MemberService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    @PostMapping
    public void signUp(@Valid @RequestBody MemberSignUpRequestDto memberSignUpRequestDto) {
        memberService.signUp(memberSignUpRequestDto);
    }

    @PostMapping("/login")
    public void login(@Valid @RequestBody LoginRequestDto loginRequestDto, HttpServletRequest request) {
        MemberResponseDto loginMember = memberService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());

        HttpSession session = request.getSession();
        session.setAttribute(Constant.LOGIN_MEMBER, loginMember);
    }
}
