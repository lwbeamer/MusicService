package com.example.server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    @ToString.Exclude
    private List<Artist> artists = new ArrayList<>();

    @Column(nullable = false, length = 32)
    private String name;

    @Column(length = 256)
    private String description;

    @Column(length = 256)
    private String link;


}
