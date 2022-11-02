package com.example.server.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetSongByAlbumRequest {
    private Long albumId;
}
