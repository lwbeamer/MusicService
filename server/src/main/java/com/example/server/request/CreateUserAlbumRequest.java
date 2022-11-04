package com.example.server.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserAlbumRequest {
    private String imageLink;
    private String name;
    private Long userId;
}
