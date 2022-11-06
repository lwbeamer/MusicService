package com.example.server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "uzer")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Uzer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 32)
    private String name;

    @Column(nullable = false, length = 32)
    private String surname;

    @Column(nullable = false, length = 32)
    private String login;

    @Column(nullable = false, length = 128)
    private String password;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;

    @JoinColumn(name = "id_sub")
    @ManyToOne(fetch = FetchType.EAGER)
    private Subscription subId;

    @Column(name = "sub_start", columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP")
    private OffsetDateTime subStart;

    @Column(name = "profile_image_link")
    private String link;

    @JoinColumn(nullable = false, name = "id_country")
    @ManyToOne(fetch = FetchType.EAGER)
    private Country countryId;


    public Uzer(String name, String surname, String login, String password) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
    }
}
