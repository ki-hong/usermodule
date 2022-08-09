package com.module.usermodule.user.controller;

import com.module.usermodule.mail.service.EmailSender;
import com.module.usermodule.user.dto.UserDto;
import com.module.usermodule.user.entity.Users;
import com.module.usermodule.user.mapper.UserMapper;
import com.module.usermodule.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserMapper userMapper;
    private UserService userService;

    private EmailSender emailSender;

    public UserController(UserMapper userMapper, UserService userService, EmailSender emailSender) {
        this.userMapper = userMapper;
        this.userService = userService;
        this.emailSender = emailSender;
    }

    @PostMapping("/join")
    public ResponseEntity joinUser(@RequestBody UserDto.UserPostDto userPostDto){
        //회원가입 요청이 들어올 시, 휴대폰 인증이 필요하며, -> 휴대폰 인증을 확인할 수 있는 내용이 userPostDto에 포함되어야 함
        if(!userPostDto.getAuthentication().equals("OK")) throw new RuntimeException("휴대폰 인증이 되지 않았습니다");
        Users user=userService.saveUser(userMapper.userPostToUsers(userPostDto));
        //이메일 인증 또한 필요하다.
        emailSender.sendEmail(new String[]{user.getEmail()});
        return new ResponseEntity<>(userMapper.uesrsToUserResponseDto(user), HttpStatus.CREATED);
    }

    @GetMapping("/activate/{user-email}")
    public ResponseEntity activateUser(@PathVariable("user-email") String userEmail){
        Users users = userService.emailSuccess(userEmail);
        return new ResponseEntity<>(userMapper.uesrsToUserResponseDto(users),HttpStatus.OK);
    }
//
//    @PatchMapping("/phone")
//    public ResponseEntity activateUser(){
//
//    }

}
