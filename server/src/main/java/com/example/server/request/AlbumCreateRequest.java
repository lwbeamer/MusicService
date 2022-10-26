package com.example.server.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlbumCreateRequest {
    private String userId;
    private String name;
    private String description;
}
