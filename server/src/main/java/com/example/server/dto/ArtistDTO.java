package com.example.server.dto;


import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode

public class ArtistDTO {
    private Long id;
    private Long userId;
    private String name;
    private String description;
    private Long orgId;
    private String orgName;

    private String imgLink;

    public ArtistDTO(Long id, Long userId, String name, String description) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.description = description;
    }

    public ArtistDTO(Long id, Long userId, String name, String description, String imgLink) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.imgLink = imgLink;
    }
}
