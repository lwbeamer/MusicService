package com.example.server.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AlbumCreateRequest {
    private List<String> artistNames;
    private String name;
    private String description;
    private String link;
}
