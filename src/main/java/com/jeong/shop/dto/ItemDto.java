package com.jeong.shop.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

// thymeleaf 예제를 위한 itemDto

@Getter
@Setter
public class ItemDto {

    private Long id;

    private String itemName;

    private Integer price;

    private String itemDetail;;

    private String sellStatCd;

    private LocalDateTime regTime;

    private LocalDateTime updateTime;
}
