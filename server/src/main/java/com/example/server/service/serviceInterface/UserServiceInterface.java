package com.example.server.service.serviceInterface;

import com.example.server.dto.*;

import com.example.server.entity.Song;
import com.example.server.entity.UserAlbums;
import com.example.server.response.FindResponse;

import java.util.List;

public interface UserServiceInterface {
    void setSub(String login, String sub);

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

    boolean createUserAlbum(String imageLink, String name, Long userId);

    UserAlbumsDTO getUserAlbum(Long userId);

    boolean addSongToUserAlbum(Long songId, Long userId);

    List<SongDTO> getUserAlbumSongs(Long userId);

    List<UserAlbumsDTO> getLastUserAlbums(Long count);

    void deleteSongFromPlaylist(Long userId, Long songId);

    ArtistDTO getArtistById(Long userID);

    List<SubscriptionDTO> getAllSubs();

    SubscriptionDTO getSubById(Long subId);

    ArtistDTO getArtistIdByName(String name);


}
