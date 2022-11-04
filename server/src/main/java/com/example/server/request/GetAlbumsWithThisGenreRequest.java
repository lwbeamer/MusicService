package com.example.server.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetAlbumsWithThisGenreRequest {
    private Long count;
    private String genre;
}
