package com.module.usermodule.security.auth;

import com.module.usermodule.user.entity.Users;
import com.module.usermodule.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        Users users = userRepository.findByEmail(userEmail).orElseThrow(()->new RuntimeException());
        return new PrincipalDetails(users);
    }
}
