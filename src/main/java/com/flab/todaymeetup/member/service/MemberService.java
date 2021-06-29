package com.flab.todaymeetup.member.service;

import com.flab.todaymeetup.member.domain.Member;
import com.flab.todaymeetup.member.dto.LoginRequestDto;
import com.flab.todaymeetup.member.dto.MemberResponseDto;
import com.flab.todaymeetup.member.dto.MemberSignUpRequestDto;
import com.flab.todaymeetup.member.exception.EmailDuplicateException;
import com.flab.todaymeetup.member.mapper.MemberMapper;
import com.flab.todaymeetup.security.Sha256Encryptor;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;
    private final Sha256Encryptor sha256Encryptor;

    public void signUp(MemberSignUpRequestDto memberSignUpRequestDto) {
        if (isExistsEmail(memberSignUpRequestDto.getEmail())) {
            throw new EmailDuplicateException(memberSignUpRequestDto.getEmail());
        }

        String encryptedPassword = sha256Encryptor.encrypt(memberSignUpRequestDto.getPassword());
        Member newMember = memberSignUpRequestDto.toEntity(encryptedPassword);
        memberMapper.signUp(newMember);
    }

    public boolean isExistsEmail(String email) {
        return memberMapper.isExistsEmail(email);
    }

    public void login(LoginRequestDto loginRequestDto, HttpSession session) {
        Member member = memberMapper.findByEmail(loginRequestDto.getEmail());

        if (member == null) {
            throw new IllegalArgumentException("존재하지 않는 사용자입니다.");
        }

        if (!isPasswordMatch(loginRequestDto.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }

        session.setAttribute("loginMember", toResponseDto(member));
    }

    public boolean isPasswordMatch(String inputPassword, String memberPassword) {
        return sha256Encryptor.encrypt(inputPassword).equals(memberPassword);
    }

    public MemberResponseDto toResponseDto(Member member) {
        return MemberResponseDto.builder()
                                .id(member.getId())
                                .email(member.getEmail())
                                .name(member.getName())
                                .phone(member.getPhone())
                                .build();
    }
}
