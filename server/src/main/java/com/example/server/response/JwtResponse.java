package com.example.server.response;

import com.example.server.entity.Country;
import com.example.server.entity.Subscription;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String name;
    private String surname;
    private String username;
    private String role;
    private Subscription subId;
    private String subStart;
    private String countryId;

    public JwtResponse(String token, Long id, String name, String surname, String username, String role, Subscription subId, OffsetDateTime subStart, Country countryId) {
        this.token = token;
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.role = role;
        this.subId = subId;

        this.countryId = countryId.getName();

    }
}
