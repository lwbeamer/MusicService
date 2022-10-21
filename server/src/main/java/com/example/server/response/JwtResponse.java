package com.example.server.response;

import com.example.server.entity.Country;
import com.example.server.entity.Role;
import com.example.server.entity.Subscription;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;

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
    private String subId;
    private String subStart;
    private String countryId;

    public JwtResponse(String token, Long id, String name, String surname, String username, String role, Subscription subId, OffsetDateTime subStart, Country countryId) {
        this.token = token;
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.role = role;
        if (subId == null) {
            this.subId = null;
        } else {
            this.subId = subId.toString();
        }
        if (subStart == null) {
            this.subStart = null;
        } else {
            this.subStart = subStart.toString();
        }

        this.countryId = countryId.getName();

    }
}
