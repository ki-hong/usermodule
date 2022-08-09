package com.module.usermodule.user.controller;

import com.module.usermodule.user.dto.UserDto;
import com.module.usermodule.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

//    UserService
//
//    @PostMapping("/join")
//    public ResponseEntity joinUser(@RequestBody UserDto.UserPostDto userPostDto){
//        //회원가입 요청이 들어올 시, 휴대폰 인증이 필요하며, -> 휴대폰 인증을 확인할 수 있는 내용이 userPostDto에 포함되어야 함
//
//
//        //이메일 인증 또한 필요하다.
//
//        return ResponseEntity<>();
//    }
//
//    @PatchMapping("/activate")
//    public ResponseEntity activateUser(){
//
//    }
//
//    @PatchMapping("/phone")
//    public ResponseEntity activateUser(){
//
//    }

}
