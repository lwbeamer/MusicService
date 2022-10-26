package com.example.server.service;

import com.example.server.entity.Album;
import com.example.server.entity.Artist;
import com.example.server.entity.Organisation;
import com.example.server.entity.Uzer;
import com.example.server.repository.AlbumRepository;
import com.example.server.repository.ArtistRepository;
import com.example.server.repository.OrganisationRepository;
import com.example.server.repository.UserRepository;
import com.example.server.service.serviceInterface.ArtistServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistService implements ArtistServiceInterface {

    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrganisationRepository organisationRepository;

    @Override
    public void addArtist(String description, String login, String name) {
        Optional<Uzer> user = userRepository.findByLogin(login);
        Artist artist = new Artist(name, description);
        artist.setUzerId(user.get());
        artistRepository.save(artist);
    }

    @Override
    public List<Organisation> getAllOrganisation() {
        return organisationRepository.getAllOrganisations().get();
    }

    @Override
    public void addAlbum(Long userId, String name, String description) {
        Album album = new Album();
        Optional<Uzer> user = userRepository.findById(userId);
        Optional<Artist> artist = artistRepository.findByUzerId(user.get());
        album.setName(name);
        album.setType("Отсутствуют треки");
        album.setDescription(description);
        artist.get().getAlbums().add(album);
        album.getArtists().add(artist.get());
        albumRepository.save(album);
        artistRepository.save(artist.get());
    }
}
