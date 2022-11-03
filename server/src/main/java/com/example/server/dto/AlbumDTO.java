package com.example.server.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class AlbumDTO implements Serializable {
    private Long id;
    private String type;
    private List<String> artistNames;
    private String name;
    private String description;
    private String link;

    public AlbumDTO(Long id, String type, String name, String description, String link) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.description = description;
        this.link = link;
    }
}
