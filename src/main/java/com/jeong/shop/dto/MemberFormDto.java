package com.jeong.shop.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MemberFormDto {

    private String userId;

    private String password;

    private String name;

    private String phoneNum;

    private String email;

    private String address;

}
