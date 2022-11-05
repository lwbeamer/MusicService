package com.example.server.service.serviceInterface;

import com.example.server.dto.*;

import com.example.server.entity.Song;
import com.example.server.entity.UserAlbums;
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

    AlbumDTO getAlbumBySongID(Long songId);

    List<GenreDTO> getAllGenres();

    List<CountryDTO> getAllCountry();

    boolean checkSongInPlaylist(Long userId, Long songId);

    boolean checkSubRequest(Long userId);

    List<AlbumDTO> getAlbumsByGenre(Long count, String genre);

    void createUserAlbum(String imageLink, String name, Long userId);

    UserAlbumsDTO getUserAlbum(Long userId);

    void addSongToUserAlbum(Long songId,Long userId);

    List<SongDTO> getUserAlbumSongs(Long userId);

    List<UserAlbumsDTO> getLastUserAlbums(Long count);

    void deleteSongFromPlaylist(Long userId,Long songId);

}
