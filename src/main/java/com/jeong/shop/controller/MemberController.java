package com.jeong.shop.controller;

import com.jeong.shop.dto.MemberFormDto;
import com.jeong.shop.entity.MemberEntity;
import com.jeong.shop.sevice.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping(value="/members")
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    @GetMapping(value = "/join")
    public String memberJoin(Model model){
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "member/memberJoin";
    }

    @PostMapping(value="/join")
    public String memberJoin(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "member/memberJoin";
        }

        try {
            MemberEntity member = MemberFormDto.createMemberEntity(memberFormDto, passwordEncoder);
            memberService.saveMember(member);
        } catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "member/memberJoin";
        }

        return "redirect:/";
    }

    // 로그인
    @GetMapping(value = "/login")
    public String memberLogin(){
        return "member/memberLogin";
    }

    @GetMapping(value="/login/error")
    public String memberLoginError(Model model){
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");
        return "member/memberLogin";
    }
}
