package com.example.server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "artist")
@Getter
@Setter
@NoArgsConstructor

public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "id_uzer")
    @OneToOne(fetch = FetchType.EAGER)
    private Uzer uzerId;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "Artists_Albums",
            joinColumns = {@JoinColumn(name = "id_artist")},
            inverseJoinColumns = {@JoinColumn(name = "id_album")}
    )

    private List<Album> albums = new ArrayList<>();


    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "Artists_Song",
            joinColumns = {@JoinColumn(name = "id_artist")},
            inverseJoinColumns = {@JoinColumn(name = "id_song")}
    )

    private List<Song> songs = new ArrayList<>();
    @Column(nullable = false, length = 256)
    private String description;

    @Column(nullable = false, length = 32)
    private String name;

    @JoinColumn(name = "id_org")
    @ManyToOne(fetch = FetchType.EAGER)
    private Organisation organisation;

    public Artist(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
