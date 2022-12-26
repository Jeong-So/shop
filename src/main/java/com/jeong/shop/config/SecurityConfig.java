// Spring Security config 파일
package com.jeong.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity   // @EnableWebSecurity 어노테이션 선언 시 SpringSecurityFilterChain이 자동으로 포함
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // spring security 5.7이상에서 더 이상 WebSecurityConfigurerAdapter 미지원 : 다음 번에 해결책 찾기
    // https://honeywater97.tistory.com/264
    // https://cupdisin.tistory.com/91
    // https://ssdragon.tistory.com/108

    // http 요청에 대한 보안 설정 : 페이지 권한 설정, 로그인 페이지 설정, 로그아웃 메소드 등에 대한 설정 작성
    @Override
    protected void configure(HttpSecurity http) throws Exception {

    }

    // BCryptPasswordEncoder의 해시 함수를 이용하여 비밀번호를 암호화하여 저장
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
