package com.example.server.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SongCreateRequest {
    private List<String> artistNames;
    private List<String> featuresName;
    private String name;
    private Long duration;
    private String albumName;
    private String genre;
    private String link;
}
