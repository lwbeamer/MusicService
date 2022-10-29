package com.example.server.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SongCreateRequest {
    private Long userId;
    private String name;
    private Long duration;
    private String albumName;
    private String genre;
    private String link;
}
