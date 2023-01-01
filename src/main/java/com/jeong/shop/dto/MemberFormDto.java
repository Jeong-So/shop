package com.jeong.shop.dto;

import com.jeong.shop.constant.Role;
import com.jeong.shop.entity.MemberEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Getter
@Setter
public class MemberFormDto {

    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z]).{6,16}")
    private String username;

    @NotBlank
    @Length(min=8, max=16)
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}",message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 16자의 비밀번호여야 합니다.")
    private String password;

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    @Pattern(regexp = "^01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$", message = "전화번호 형식이 올바르지 않습니다.")
    private String phoneNum;

    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식이 올바르지 않습니다.")
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    private String email;

    private String address;

    // MemberFormDto 를 MemberEntity로 변환시키는 메소드
    public static MemberEntity createMemberEntity(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder){

        MemberEntity member = new MemberEntity();
        member.setUsername(memberFormDto.getUsername());
        String password = passwordEncoder.encode(memberFormDto.getPassword());  // 비밀번호 암호화
        member.setPassword(password);
        member.setName(memberFormDto.getName());
        member.setPhoneNum(memberFormDto.getPhoneNum());
        member.setEmail(memberFormDto.getEmail());
        member.setRole(Role.USER);
        member.setAddress(memberFormDto.getAddress());

        return member;
    }

}
