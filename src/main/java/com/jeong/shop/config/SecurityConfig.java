// Spring Security 시스템의 전반적인 설정(configuration)을 담당
// 1. Filter 추가   2. URI별 접근권한 설정   3. AuthenticationManager가 검증을 요청할 Provider 추가
package com.jeong.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity   // @EnableWebSecurity 어노테이션 선언 시 SpringSecurityFilterChain이 자동으로 포함
public class SecurityConfig {
    // spring security 5.7이상에서 더 이상 WebSecurityConfigurerAdapter 미지원

    // http 요청에 대한 보안 설정 : 페이지 권한 설정, 로그인 페이지 설정, 로그아웃 메소드 등에 대한 설정 작성
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http.build();
    }

    // BCryptPasswordEncoder의 해시 함수를 이용하여 비밀번호를 암호화하여 저장
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
