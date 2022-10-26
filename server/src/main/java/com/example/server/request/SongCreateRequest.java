package com.example.server.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SongCreateRequest {
    private String userId;
    private String name;
    private Integer duration;
    private String albumName;
    private String genre;
    private String link;
}
