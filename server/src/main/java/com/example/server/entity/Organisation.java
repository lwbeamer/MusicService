package com.example.server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "organisation")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Organisation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 32, unique = true)
    private String name;

    @Column(nullable = false, length = 256)
    private String description;

    @JoinColumn(nullable = false, name = "id_country")
    @ManyToOne(fetch = FetchType.EAGER)
    private Country countryId;

    public Organisation(String name, String description) {
        this.name = name;
        this.description = description;
    }

}
