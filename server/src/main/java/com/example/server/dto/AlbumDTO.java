package com.example.server.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class AlbumDTO {
    private Long id;
    private String type;
    private List<String> artistNames;
    private String name;
    private String description;

    public AlbumDTO(Long id, String type, String name, String description) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.description = description;
    }
}
