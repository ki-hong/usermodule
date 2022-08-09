package com.module.usermodule.user.service;

import com.module.usermodule.user.entity.Users;
import com.module.usermodule.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Users saveUser(Users user){
        Optional<Users> optionalUser =userRepository.findByEmail(user.getEmail());
        if(optionalUser.isPresent())throw new RuntimeException("이미 가입된 이메일입니다.");

        return userRepository.save(user);
    }

    public Users emailSuccess(String userEmail){
        Optional<Users> optionalUsers = userRepository.findByEmail(userEmail);
        Users findUser = optionalUsers.orElseThrow(()-> new RuntimeException());
        findUser.setStatus(Users.Status.ACTIVE);
        return userRepository.save(findUser);
    }
}
