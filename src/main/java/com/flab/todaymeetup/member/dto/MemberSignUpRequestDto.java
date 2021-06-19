package com.flab.todaymeetup.member.dto;

import com.flab.todaymeetup.member.domain.Member;
import com.flab.todaymeetup.util.security.EncryptUtil;
import lombok.Getter;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
public class MemberSignUpRequestDto {

    @Email(message = "올바른 이메일 형식이 아닙니다.")
    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;

    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Pattern(message = "비밀번호는 최소 하나의 문자, 숫자, 특수문자를 포함하여 총 8자 이상이어야 합니다.",
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$")
    private String password;

    @NotBlank(message = "휴대폰 번호를 입력해주세요.")
    @Pattern(message = "올바른 휴대폰 번호 양식이 아닙니다.",
            regexp = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$")
    private String phone;

    public Member toEntity(){
        return Member.builder()
                    .email(email)
                    .name(name)
                    .password(EncryptUtil.encryptSha256(password))
                    .phone(phone)
                    .build();
    }
}
