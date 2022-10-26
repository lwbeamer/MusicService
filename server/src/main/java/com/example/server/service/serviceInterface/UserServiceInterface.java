package com.example.server.service.serviceInterface;

import com.example.server.entity.Subscription;
import com.example.server.entity.Uzer;

import java.util.Optional;

public interface UserServiceInterface {
    void setSub(Optional<Uzer> user, Optional<Subscription> sub);
}
