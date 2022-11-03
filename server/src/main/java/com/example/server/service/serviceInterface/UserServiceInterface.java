package com.example.server.service.serviceInterface;

import com.example.server.dto.AlbumDTO;
import com.example.server.dto.SongDTO;

import com.example.server.response.FindResponse;

import java.util.List;

public interface UserServiceInterface {
    void setSub(String login, String sub);

    void addSongToPlayList(Long userId, Long songId);

    List<SongDTO> getPlayList(Long userId);

    FindResponse findSong(String name);

    SongDTO getSong(Long songId);

    List<SongDTO> getSongsById(Long albumId);

    List<AlbumDTO> getLastAlbums(Long count);

    AlbumDTO getAlbumById(Long id);

}
