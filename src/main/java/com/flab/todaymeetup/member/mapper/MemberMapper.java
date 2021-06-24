package com.flab.todaymeetup.member.mapper;

import com.flab.todaymeetup.member.domain.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    void signUp(Member member);

    boolean isExistsEmail(String email);
}
