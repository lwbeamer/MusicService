package com.example.server.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlbumCreateRequest {
    private Long userId;
    private String name;
    private String description;
    private String link;
}
