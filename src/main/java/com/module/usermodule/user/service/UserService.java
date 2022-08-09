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

    private Users saveUser(Users user){
        Optional<Users> optionalUser =userRepository.findByEmail(user.getEmail());
        Users findUser = optionalUser.orElseThrow(()->new RuntimeException());
        return findUser;
    }
}
