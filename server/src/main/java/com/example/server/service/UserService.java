package com.example.server.service;

import com.example.server.entity.Subscription;
import com.example.server.entity.Uzer;
import com.example.server.repository.UserRepository;
import com.example.server.service.serviceInterface.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void setSub(Optional<Uzer> user, Optional<Subscription> sub) {
        userRepository.addSubToUser(user.get().getId(), sub.get().getId());
    }
}
