package com.module.usermodule.user.service;

import com.module.usermodule.user.entity.Users;
import com.module.usermodule.user.repository.UserRepository;
import com.module.usermodule.util.AES256Util;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Optional;

@Transactional
@Service
public class UserService {

    private final UserRepository userRepository;
    private final AES256Util aes256Util;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, AES256Util aes256Util, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.aes256Util = aes256Util;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Users saveUser(Users user){

        Optional<Users> optionalUser =userRepository.findByEmail(user.getEmail());
        if(optionalUser.isPresent())throw new RuntimeException("이미 가입된 이메일입니다.");

        return userRepository.save(user);
    }

    public Users emailSuccess(String aesCode) throws GeneralSecurityException, UnsupportedEncodingException {
        String userEmail = aes256Util.decrypt(aesCode).substring(16);
        Optional<Users> optionalUsers = userRepository.findByEmail(userEmail);
        Users findUser = optionalUsers.orElseThrow(()-> new RuntimeException());
        findUser.setStatus(Users.Status.ACTIVE);
        return userRepository.save(findUser);
    }
}
