package com.example.server.response;

import com.example.server.dto.AlbumDTO;
import com.example.server.dto.SongDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@Setter
public class FindResponse {
    private List<SongDTO> songs;
    private List<AlbumDTO> albums;
    private List<AlbumDTO> albumsArtist;
    private List<SongDTO> songsArtist;

    public FindResponse(List<SongDTO> songs, List<AlbumDTO> albums, List<AlbumDTO> albumsArtist, List<SongDTO> songsArtist) {
        this.songs = songs;
        this.albums = albums;
        this.albumsArtist = albumsArtist;
        this.songsArtist = songsArtist;
    }
}
