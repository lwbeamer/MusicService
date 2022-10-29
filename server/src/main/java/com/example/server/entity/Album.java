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
@Table(name = "album")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 32)
    private String type;

    @ManyToMany(mappedBy = "albums")
    private Set<Artist> artists = new HashSet<Artist>();

    @Column(nullable = false,length = 32)
    private String name;

    @Column(length = 256)
    private String description;

    public Album(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
