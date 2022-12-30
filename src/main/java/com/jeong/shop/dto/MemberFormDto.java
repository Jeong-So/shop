package com.jeong.shop.dto;

import com.jeong.shop.constant.Role;
import com.jeong.shop.entity.MemberEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;


@Getter
@Setter
public class MemberFormDto {

    private String username;

    private String password;

    private String name;

    private String phoneNum;

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
