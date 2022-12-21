// thymeleaf 예제

package com.jeong.shop.controller;

import com.jeong.shop.dto.ItemDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value="/thymeleaf")
public class ThymeleafExController {

    @GetMapping(value = "/ex01")
    public String thymeleafExample01(Model model){
        model.addAttribute("data", "타임리프 예제 입니다.");
        // model 객체를 이용해 뷰에 전달한 데이터를 key, value 구조로 넣어줌
        return "thymeleafEx/thymeleafEx01";
        // templates 폴더 기준 뷰의 위치와 이름(thymeleafEx01) 반환
    }

    // thymeleaf th:text 예제
    // 모델에 데이터를 담아서 뷰에 전달
    @GetMapping(value = "/ex02")
    public String thymeleafExample02(Model model){
        ItemDto itemDto = new ItemDto();
        itemDto.setItemDetail("상품 상세 설명");
        itemDto.setItemName("테스트 상품01");
        itemDto.setPrice(10000);
        itemDto.setRegTime(LocalDateTime.now());

        model.addAttribute("itemDto", itemDto);
        return "thymeleafEx/thymeleafEx02";
    }

    // thymeleaf th:each (반복문) 예제
    @GetMapping(value = "/ex03")
    public String thymeleafExample03(Model model){

        List<ItemDto> itemDtoList = new ArrayList<>();

        for(int i=1; i<=10 ; i++) {
            ItemDto itemDto = new ItemDto();
            itemDto.setItemDetail("상품 상세 설명"+i);
            itemDto.setItemName("테스트 상품"+i);
            itemDto.setPrice(1000*i);
            itemDto.setRegTime(LocalDateTime.now());

            itemDtoList.add(itemDto);
        }
        model.addAttribute("itemDtoList", itemDtoList);
        return "thymeleafEx/thymeleafEx03";
    }

    // thymeleaf th:if th:unless (조건문) / th:switch th:case (조건문) 예제
    @GetMapping(value = "/ex04")
    public String thymeleafExample04(Model model){

        List<ItemDto> itemDtoList = new ArrayList<>();

        for(int i=1; i<=10 ; i++) {
            ItemDto itemDto = new ItemDto();
            itemDto.setItemDetail("상품 상세 설명"+i);
            itemDto.setItemName("테스트 상품"+i);
            itemDto.setPrice(1000*i);
            itemDto.setRegTime(LocalDateTime.now());

            itemDtoList.add(itemDto);
        }
        model.addAttribute("itemDtoList", itemDtoList);
        return "thymeleafEx/thymeleafEx04";
    }

    // thymeleaf th:href 예제 : 링크 처리
    @GetMapping(value = "/ex05")
    public String thymeleafExample05() {
        return "thymeleafEx/thymeleafEx05";
    }

    // thymeleaf th:href 예제 : 링크 처리 + 파라미터 전달
    @GetMapping(value = "/ex06")
    public String thymeleafExample06(String param1, String param2, Model model) {
        model.addAttribute("param1", param1);
        model.addAttribute("param2", param2);
        return "thymeleafEx/thymeleafEx06";
    }
}
