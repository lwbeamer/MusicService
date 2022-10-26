package com.example.server.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAddSongRequest {
    private Integer userId;
    private Integer songId;
}
