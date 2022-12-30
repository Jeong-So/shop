package com.jeong.shop.entity;

import com.jeong.shop.constant.Role;
import com.jeong.shop.dto.MemberFormDto;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Data
@Table(name="member")
@Entity
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)       // 기본키 생성전략 설정 : GenerationType.AUTO (JPA가 IDENTITY, SEQUENCE, TABLE 중 자동 선택)
    private Long memberId;                                // 멤버 아이디(PK, 자동 생성)

    @Column(nullable = false, length = 15, unique = true)
    private String username;                              // 회원 아이디(가입 시)

    @Column(nullable = false)
    private String password;                              // 회원 패스워드

    @Column(nullable = false)
    private String name;                                  // 회원 이름

    @Column(nullable = false)
    private String phoneNum;                              // 회원 전화번호

    @Column(nullable = false)
    private String email;                                 // 회원 이메일

    @Enumerated(EnumType.STRING)
    private Role role;                                    // 회원 구분

    private String address;                               // 회원 주소

    // MemberFormDto 를 MemberEntity로 변환시키는 메소드
//    public static MemberEntity createMemberEntity(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder){
//
//        MemberEntity member = new MemberEntity();
//        member.setUsername(memberFormDto.getUsername());
//        String password = passwordEncoder.encode(memberFormDto.getPassword());  // 비밀번호 암호화
//        member.setPassword(password);
//        member.setName(memberFormDto.getName());
//        member.setPhoneNum(memberFormDto.getPhoneNum());
//        member.setEmail(memberFormDto.getEmail());
//        member.setRole(Role.USER);
//        member.setAddress(memberFormDto.getAddress());
//
//        return member;
//    }

}
