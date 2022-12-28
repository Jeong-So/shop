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
    @Column(name="member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long memberId;

    @Column(name="user_id", nullable = false, length = 15, unique = true)
    private String userId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(name="phone_number", nullable = false)
    private String phoneNum;

    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column
    private String address;

    public static MemberEntity createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder){

        MemberEntity member = new MemberEntity();
        member.setUserId(memberFormDto.getUserId());
        String password = passwordEncoder.encode(member.getPassword());
        member.setPassword(password);
        member.setName(memberFormDto.getName());
        member.setPhoneNum(member.getPhoneNum());
        member.setEmail(memberFormDto.getEmail());
        member.setRole(Role.USER);
        member.setAddress(memberFormDto.getAddress());

        return member;
    }

}
