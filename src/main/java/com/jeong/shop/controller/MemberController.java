package com.jeong.shop.controller;

import com.jeong.shop.dto.MemberFormDto;
import com.jeong.shop.sevice.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value="/members")
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping(value = "/join")
    public String MemberJoin(Model model){
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "member/memberJoin";
    }
}
