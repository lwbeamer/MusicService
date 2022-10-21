package com.example.server.controller;

import com.example.server.config.JwtUtils;
import com.example.server.entity.Country;
import com.example.server.entity.Role;

import com.example.server.entity.Uzer;
import com.example.server.entity.entityhelper.ERole;
import com.example.server.repository.CountryRepository;
import com.example.server.repository.RoleRepository;
import com.example.server.repository.UserRepository;
import com.example.server.request.LoginRequest;
import com.example.server.request.SignupRequest;
import com.example.server.response.JwtResponse;
import com.example.server.response.MessageResponse;
import com.example.server.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    CountryRepository countryRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getLogin(),
                loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String roles = userDetails.getAuthorities().toString();
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getName(),
                userDetails.getSurname(),
                userDetails.getUsername(),
                roles,
                userDetails.getSubId(),
                userDetails.getSubStart(),
                userDetails.getCountryId()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest) {

        if (userRepository.existsByLogin(signupRequest.getLogin())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is exist"));
        }


        Uzer user = new Uzer(signupRequest.getName(),
                signupRequest.getSurname(),
                signupRequest.getLogin(),
                passwordEncoder.encode(signupRequest.getPassword())
        );

        Country country = countryRepository.findByName(signupRequest.getCountryId()).orElseThrow(() -> new RuntimeException("Error, This country is not found"));
        ;
        user.setCountryId(country);

        String reqRoles = signupRequest.getRole();
        Role roles;

        if (reqRoles == null) {
            Role userRole = roleRepository
                    .findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error, Role USER is not found"));
            roles = userRole;
        } else {
            switch (reqRoles) {
                case "admin":
                    Role adminRole = roleRepository
                            .findByName(ERole.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error, Role ADMIN is not found"));
                    roles = adminRole;

                    break;
                case "organization":
                    Role modRole = roleRepository
                            .findByName(ERole.ROLE_ORGANIZATION)
                            .orElseThrow(() -> new RuntimeException("Error, Role MODERATOR is not found"));
                    roles = modRole;

                    break;
                case "artist":
                    Role artistRole = roleRepository
                            .findByName(ERole.ROLE_ARTIST)
                            .orElseThrow(() -> new RuntimeException("Error, Role ARTIST is not found"));
                    roles = artistRole;
                    break;
                default:
                    Role userRole = roleRepository
                            .findByName(ERole.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Error, Role USER is not found"));
                    roles = userRole;
            }

        }
        user.setRole(roles);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User CREATED"));
    }
}
