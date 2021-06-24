package com.flab.todaymeetup.member.service;

import com.flab.todaymeetup.member.domain.Member;
import com.flab.todaymeetup.member.dto.MemberSignUpRequestDto;
import com.flab.todaymeetup.member.exception.EmailDuplicateException;
import com.flab.todaymeetup.member.mapper.MemberMapper;
import com.flab.todaymeetup.security.Sha256Encryptor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;
    private final Sha256Encryptor sha256Encryptor;

    public void signUp(MemberSignUpRequestDto memberSignUpRequestDto) {
        if( isExistsEmail(memberSignUpRequestDto.getEmail()) ) {
            throw new EmailDuplicateException(memberSignUpRequestDto.getEmail());
        }

        String encryptedPassword = sha256Encryptor.encrypt(memberSignUpRequestDto.getPassword());
        Member newMember = memberSignUpRequestDto.toEntity(encryptedPassword);
        memberMapper.signUp(newMember);
    }

    public boolean isExistsEmail(String email) {
        return memberMapper.isExistsEmail(email);
    }
}
