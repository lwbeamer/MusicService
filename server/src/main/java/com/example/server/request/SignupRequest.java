package com.example.server.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {
    private String name;
    private String surname;
    private String login;
    private String password;
    private String role;
    private String countryId;
    private String profileImage;
}
