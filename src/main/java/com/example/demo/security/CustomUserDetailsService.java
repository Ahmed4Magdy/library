package com.example.demo.security;

import com.example.demo.entity.SystemUser;
import com.example.demo.repo.SystemUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor



public class CustomUserDetailsService implements UserDetailsService {

    private final SystemUserRepository systemUserRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        SystemUser user=systemUserRepository.findByUsername(username).orElseThrow(()->
                new RuntimeException("user not found"));
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();

    }
}
