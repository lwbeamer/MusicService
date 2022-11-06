package com.example.server.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetAlbumsByArtistIdRequest {
    private Long artistId;
}
