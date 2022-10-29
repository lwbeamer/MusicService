package com.example.server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
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

    private Set<Album> albums = new HashSet<Album>();
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "Artists_Song",
            joinColumns = {@JoinColumn(name = "id_artist")},
            inverseJoinColumns = {@JoinColumn(name = "id_song")}
    )

    private Set<Song> songs = new HashSet<Song>();
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
