package com.flab.todaymeetup.member.service;

import com.flab.todaymeetup.member.domain.Member;
import com.flab.todaymeetup.member.dto.MemberSignUpRequestDto;
import com.flab.todaymeetup.member.exception.EmailDuplicateException;
import com.flab.todaymeetup.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;

    public void signUp(MemberSignUpRequestDto memberSignUpRequestDto) {
        if( existsEmail(memberSignUpRequestDto.getEmail()) ) {
            throw new EmailDuplicateException(memberSignUpRequestDto.getEmail());
        }

        Member newMember = memberSignUpRequestDto.toEntity();
        memberMapper.signUp(newMember);
    }

    public boolean existsEmail(String email) {
        return memberMapper.existsEmail(email);
    }
}
