package com.example.server.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAddSongRequest {
    private Long userId;
    private Long songId;
}
