package com.example.server.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "country")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 32, unique = true)
    private String name;

}
