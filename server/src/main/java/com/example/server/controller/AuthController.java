package com.example.server.controller;

import com.example.server.config.JwtUtils;
import com.example.server.entity.Admin;
import com.example.server.entity.Country;
import com.example.server.entity.Role;

import com.example.server.entity.Uzer;
import com.example.server.entity.entityhelper.ERole;
import com.example.server.repository.AdminRepository;
import com.example.server.repository.CountryRepository;
import com.example.server.repository.RoleRepository;
import com.example.server.repository.UserRepository;
import com.example.server.request.LoginRequest;
import com.example.server.request.SignupRequest;
import com.example.server.response.JwtResponse;
import com.example.server.response.MessageResponse;
import com.example.server.dto.UserDetailsImpl;
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

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final AdminRepository adminRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CountryRepository countryRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public AuthController(AuthenticationManager authenticationManager, AdminRepository adminRepository, UserRepository userRepository, RoleRepository roleRepository, CountryRepository countryRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.adminRepository = adminRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.countryRepository = countryRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getLogin(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String roles = userDetails.getAuthorities().toString();
        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getName(), userDetails.getSurname(), userDetails.getUsername(), roles, userDetails.getSubId(), userDetails.getSubStart(), userDetails.getCountryId()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest) {

        if (userRepository.existsByLogin(signupRequest.getLogin())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Данный логин занят!"));
        }


        Uzer user = new Uzer(signupRequest.getName(), signupRequest.getSurname(), signupRequest.getLogin(), passwordEncoder.encode(signupRequest.getPassword()));

        Country country = countryRepository.findByName(signupRequest.getCountryId()).orElseThrow(() -> new RuntimeException("Error: Такой страны не существует!"));
        user.setCountryId(country);

        String reqRoles = signupRequest.getRole();
        Role roles;

        if (reqRoles == null) {
            roles = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("Error: Роли USER не существует!"));
        } else {
            roles = switch (reqRoles) {
                case "admin" ->
                        roleRepository.findByName(ERole.ROLE_ADMIN).orElseThrow(() -> new RuntimeException("Error: Роли ADMIN не существует!"));
                case "artist" ->
                        roleRepository.findByName(ERole.ROLE_ARTIST).orElseThrow(() -> new RuntimeException("Error: Роли ARTIST не существует!"));
                default ->
                        roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("Error: Роли USER не существует!"));
            };

        }
        user.setRole(roles);
        userRepository.save(user);
        assert reqRoles != null;
        if (reqRoles.equals("admin")) {
            Optional<Uzer> user1 = userRepository.findByLogin(signupRequest.getLogin());
            Admin admin = new Admin();
            user1.ifPresent(admin::setUzerId);
            adminRepository.save(admin);
        }
        return ResponseEntity.ok(new MessageResponse("Пользователь успешно зарегистрирован!"));
    }
}
