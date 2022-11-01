package com.example.server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="subscription")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 32, unique = true)
    private String name;

    @Column(nullable = false, length = 256, unique = true)
    private String description;

    @Column(nullable = false)
    private Long price;

}
