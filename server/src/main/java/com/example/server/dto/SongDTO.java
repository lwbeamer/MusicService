package com.example.server.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class SongDTO {
    private Long id;
    private String name;
    private String link;
    private Long duration;
    private String albumName;
    private String genreName;
    private List<String> artistNames;
    private String imageLink;

    public SongDTO(Long id, String name, String link, Long duration, String albumName, String genreName,String imageLink) {
        this.id = id;
        this.name = name;
        this.link = link;
        this.duration = duration;
        this.albumName = albumName;
        this.genreName = genreName;
        this.imageLink = imageLink;
    }
}
