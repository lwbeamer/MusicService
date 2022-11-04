package com.example.server.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckSongInPlaylistRequest {
    private Long userId;
    private Long songId;
}
