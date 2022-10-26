package com.example.server.service;

import com.example.server.dto.UserDetailsImpl;
import com.example.server.entity.Uzer;
import com.example.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Uzer user = userRepository.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException("User Not found with username: " + username));
        return UserDetailsImpl.build(user);
    }
}
