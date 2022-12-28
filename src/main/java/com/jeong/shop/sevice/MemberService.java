package com.jeong.shop.sevice;

import com.jeong.shop.entity.MemberEntity;
import com.jeong.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberEntity saveMember(MemberEntity member){
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(MemberEntity member){
        MemberEntity findMember = memberRepository.findByUserId(member.getUserId());
        if(findMember != null){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }


}
