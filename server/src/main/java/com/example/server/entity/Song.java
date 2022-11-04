package com.example.server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "song")
@Getter
@Setter
@NoArgsConstructor

public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 32)
    private String name;

    @Column(nullable = false, length = 256)
    private String link;

    @Column(name = "duration", nullable = false)
    private Long duration;

    @JoinColumn(name = "id_album")
    @ManyToOne(fetch = FetchType.EAGER)
    private Album albumId;

    @JoinColumn(name = "id_admin")
    @ManyToOne(fetch = FetchType.EAGER)
    private Admin adminId;

    @JoinColumn(name = "id_genre")
    @ManyToOne(fetch = FetchType.EAGER)
    private Genre genreId;

    @Column(name = "last_change", nullable = false)
    private OffsetDateTime last_change;

    @ManyToMany(mappedBy = "songs")
    private Set<UserAlbums> userAlbums = new HashSet<>();

    @ManyToMany(mappedBy = "songs")
    private Set<Uzer> users = new HashSet<>();

    @ManyToMany(mappedBy = "songs")
    private Set<Artist> artists = new HashSet<>();

    public Song(String name, Long duration, Album albumId, Admin adminId, Genre genreId, String link) {
        this.name = name;
        this.duration = duration;
        this.albumId = albumId;
        this.adminId = adminId;
        this.genreId = genreId;
        this.link = link;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", link='" + link + '\'' +
                ", duration=" + duration +
                ", albumId=" + albumId.getName() +
                ", genreId=" + genreId.getName() +
                ", subStart=" + last_change +
                '}';
    }
}
