package com.jeong.shop.sevice;

import com.jeong.shop.entity.MemberEntity;
import com.jeong.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 비즈니스 로직 담당하는 service 클래스

@Service
@Transactional  // 로직 처리 중 에러 발생 시 변경된 데이터를 로직 수행 이전 상태로 콜백
@RequiredArgsConstructor  // final이나 @NonNull이 붙은 필드에 생성자를 생성
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberEntity saveMember(MemberEntity member){
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    // 회원가입 중복여부 확인 메소드
    private void validateDuplicateMember(MemberEntity member){
        MemberEntity findMember = memberRepository.findByUsername(member.getUsername());
        if(findMember != null){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }


}
