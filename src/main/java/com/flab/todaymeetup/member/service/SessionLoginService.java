package com.flab.todaymeetup.member.service;

import com.flab.todaymeetup.member.dto.LoginRequestDto;
import com.flab.todaymeetup.member.dto.MemberResponseDto;
import com.flab.todaymeetup.util.SessionKeys;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionLoginService implements LoginService {

    private final MemberService memberService;
    private final HttpSession session;

    @Override
    public void login(LoginRequestDto loginRequestDto) {
        MemberResponseDto loginMember = memberService.getLoginMember(loginRequestDto);
        session.setAttribute(SessionKeys.LOGIN_MEMBER, loginMember);
    }
}
