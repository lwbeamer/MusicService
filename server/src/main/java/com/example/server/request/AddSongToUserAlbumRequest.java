package com.example.server.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddSongToUserAlbumRequest {
    private Long songId;
    private Long userId;

}
