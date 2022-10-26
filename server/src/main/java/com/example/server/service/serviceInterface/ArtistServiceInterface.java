package com.example.server.service.serviceInterface;

import com.example.server.entity.Organisation;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ArtistServiceInterface {
    void addArtist(String description, String login, String name);
    List<Organisation> getAllOrganisation();

    void addAlbum(Long userId,String name,String description);
}
