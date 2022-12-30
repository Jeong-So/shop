package com.jeong.shop.service;

import com.jeong.shop.constant.Role;
import com.jeong.shop.dto.MemberFormDto;
import com.jeong.shop.entity.MemberEntity;
import com.jeong.shop.sevice.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional  // 테스트 실행 후 롤백 처리, 이를 통해 같은 메소드를 반복적으로 테스트 가능
@TestPropertySource(locations="classpath:application-test.properties")
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public MemberEntity createMember(){

        MemberFormDto memberFormDto = new MemberFormDto();

        memberFormDto.setUsername("panda9404");
        memberFormDto.setPassword("1234");
        memberFormDto.setName("소유정");
        memberFormDto.setPhoneNum("010-9777-9404");
        memberFormDto.setEmail("panda9404@naver.com");
        memberFormDto.setAddress("서울시 노원구");

        return MemberFormDto.createMemberEntity(memberFormDto, passwordEncoder);
    }

    @Test
    @DisplayName("회원가입 테스트")
    public void saveMemberTest(){
        MemberEntity member = createMember();
        MemberEntity savedMember = memberService.saveMember(member);

        assertEquals(member.getUsername(), savedMember.getUsername());
        assertEquals(member.getPassword(), savedMember.getPassword());
        assertEquals(member.getName(), savedMember.getName());
        assertEquals(member.getPhoneNum(), savedMember.getPhoneNum());
        assertEquals(member.getEmail(), savedMember.getEmail());
        assertEquals(member.getAddress(), savedMember.getAddress());

    }

    @Test
    @DisplayName("중복 회원가입 테스트")
    public void saveDuplicateMemberTest(){
        MemberEntity member1 = createMember();
        MemberEntity member2 = createMember();
        memberService.saveMember(member1);

        Throwable e = assertThrows(IllegalStateException.class, () -> {
            memberService.saveMember(member2);
        });

        assertEquals("이미 가입된 회원입니다.", e.getMessage());

    }

}
