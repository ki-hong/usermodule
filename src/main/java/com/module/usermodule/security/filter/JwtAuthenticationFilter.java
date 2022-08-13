package com.module.usermodule.security.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.module.usermodule.security.auth.PrincipalDetails;
import com.module.usermodule.sms.mapper.SMSMapper;
import com.module.usermodule.user.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try{
            ObjectMapper mapper = new ObjectMapper();
            Users user = mapper.readValue(request.getInputStream(),Users.class);//Request에 있는 body에 값을 꺼내옴(body를 꺼내 사용하므로 나중에 값을 불러올 수 없음)

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword());

            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();

            return authentication;
        }catch (IOException e){
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        System.out.println("successfulAuthentication");
        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();

        String accessToken = JWT.create()
                .withSubject("moduleAccessToken")
                .withExpiresAt(new Date(System.currentTimeMillis() + (1000*60*30)))//Access 30분
                .withClaim("id", principalDetails.getUser().getId())
                .withClaim("userEmail", principalDetails.getUser().getEmail())
                .withClaim("username", principalDetails.getUser().getName())
                .withClaim("nickname", principalDetails.getUser().getNickname())
                .sign(Algorithm.HMAC512("${token.access}"));
        response.addHeader("moduleAccessToken", "Bearer " + accessToken);

        String refreshToken = JWT.create()
                .withSubject("moduleRefreshToken")
                .withExpiresAt(new Date(System.currentTimeMillis() + (1000*60*60*24*14)))//Refresh 14일
                .sign(Algorithm.HMAC512("${token.refresh}"));
        response.addHeader("moduleAccessToken", "Bearer " + refreshToken);
    }
}
