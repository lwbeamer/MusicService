package com.example.server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_albums")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserAlbums {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 32)
    private String name;

    @Column(length = 256,name="image_link")
    private String imageLink;

    @JoinColumn(name = "id_uzer")
    @OneToOne(fetch = FetchType.EAGER)
    private Uzer user;

    public UserAlbums(String name, String imageLink, Uzer user) {
        this.name = name;
        this.imageLink = imageLink;
        this.user = user;
    }

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "Uzer_Albums_Songs",
            joinColumns = { @JoinColumn(name = "id_user_albums") },
            inverseJoinColumns = {@JoinColumn(name = "id_song")}
    )
    @ToString.Exclude
    private Set<Song> songs = new HashSet<>();

}
