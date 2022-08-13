package com.module.usermodule.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();// form태그로만 요청이 가능하게 함
        httpSecurity.headers().frameOptions().disable(); // h2 database 연결이 필요할 때 사용
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//세션이나 쿠키를 만들지 않고 STATELESS로 진행

                .and()
                .formLogin().disable()//만약 form로그인을 사용하게 된다면 해제시켜주자.
                .httpBasic().disable()//기본적으로 제공되는 httpBasicAuth를 사용하지 않겠다라는 선언
                .authorizeRequests()//requestMapping에 대한 권한 설정 시작
                .antMatchers("/v1/user/**")
                .access("hasRole('ROLE_USER) or hasRole('ROLE_ADMIN')")
                .antMatchers("/v1/admin/**")
                .access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll();
        return httpSecurity.build();
    }

    public class CustomDsl extends AbstractHttpConfigurer<CustomDsl, HttpSecurity> {

    }

}
