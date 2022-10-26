package com.example.server.controller;

import com.example.server.entity.Subscription;
import com.example.server.entity.Uzer;
import com.example.server.repository.SubscriptionRepository;
import com.example.server.repository.UserRepository;
import com.example.server.request.SubRequest;
import com.example.server.response.MessageResponse;
import com.example.server.service.serviceInterface.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    @Autowired
    private UserServiceInterface userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @PostMapping("/addSub")
    public ResponseEntity<?> addSubToUser(@RequestBody SubRequest subRequest) {
        Optional<Uzer> user = userRepository.findByLogin(subRequest.getLogin());
        Optional<Subscription> sub = subscriptionRepository.findByName(subRequest.getSub());
        userService.setSub(user, sub);
        return ResponseEntity.ok(new MessageResponse("Подписка успешно обновлена!"));
    }
}
