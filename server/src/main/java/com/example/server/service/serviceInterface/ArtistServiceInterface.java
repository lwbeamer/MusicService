package com.example.server.service.serviceInterface;

import com.example.server.dto.AlbumDTO;
import com.example.server.entity.Organisation;

import java.util.List;


public interface ArtistServiceInterface {
    void addArtist(String description, String login, String name);

    List<Organisation> getAllOrganisation();

    void addAlbum(List<String> artistNames, String name, String description, String link) throws CloneNotSupportedException;

    void setOrganisation(Long userId, Long orgId);

    void quitFromOrganisation(Long userId);

    void addSong(List<String> artistNames, List<String> featuresNames, String name, Long duration, String albumName, String genre, String link);

    List<AlbumDTO> getAllAlbumsByArtistId(Long artistId);
}
