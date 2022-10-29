package com.example.server.service;

import com.example.server.entity.*;
import com.example.server.repository.*;
import com.example.server.service.serviceInterface.ArtistServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
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
    private GenreRepository genreRepository;
    @Autowired
    private OrganisationRepository organisationRepository;
    @Autowired
    private SongRepository songRepository;

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

    @Override
    public void setOrganisation(Long userId, Long orgId) {
        Optional<Organisation> org = organisationRepository.findById(orgId);
        Optional<Uzer> user = userRepository.findById(userId);
        Optional<Artist> artist = artistRepository.findByUzerId(user.get());
        artist.get().setOrganisation(org.get());
        artistRepository.save(artist.get());
    }

    @Override
    public void quitFromOrganisation(Long userId) {
        Optional<Uzer> user = userRepository.findById(userId);
        Optional<Artist> artist = artistRepository.findByUzerId(user.get());
        artist.get().setOrganisation(null);
        artistRepository.save(artist.get());
    }

    @Override
    public void addSong(Long userId, String name, Long duration, String albumName, String genre, String link) {
        Optional<Uzer> user = userRepository.findById(userId);
        Optional<Artist> artist = artistRepository.findByUzerId(user.get());
        Optional<Album> album = albumRepository.findById(Long.parseLong(albumRepository.getArtistAlbum(artist.get().getId(), albumName)));
        Optional<Genre> genre1 = genreRepository.findByName(genre);
        Song song = new Song(name, duration, album.get(), null, genre1.get(), link);
        song.setLast_change(OffsetDateTime.now());
        artist.get().getSongs().add(song);
        artistRepository.save(artist.get());
    }
}
