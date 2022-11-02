package com.example.server.service;

import com.example.server.entity.*;
import com.example.server.repository.*;
import com.example.server.service.serviceInterface.ArtistServiceInterface;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ArtistService implements ArtistServiceInterface {

    private final ArtistRepository artistRepository;
    private final AlbumRepository albumRepository;
    private final UserRepository userRepository;
    private final GenreRepository genreRepository;
    private final OrganisationRepository organisationRepository;


    public ArtistService(ArtistRepository artistRepository, AlbumRepository albumRepository, UserRepository userRepository, GenreRepository genreRepository, OrganisationRepository organisationRepository) {
        this.artistRepository = artistRepository;
        this.albumRepository = albumRepository;
        this.userRepository = userRepository;
        this.genreRepository = genreRepository;
        this.organisationRepository = organisationRepository;
    }

    @Override
    public void addArtist(String description, String login, String name) {
        Optional<Uzer> user = userRepository.findByLogin(login);
        Artist artist = new Artist(name, description);
        if (user.isPresent()) {
            artist.setUzerId(user.get());
            artistRepository.save(artist);
        } else {
            System.out.println("Не удалось добавить");
        }

    }

    @Override
    public List<Organisation> getAllOrganisation() {
        return organisationRepository.getAllOrganisations().get();
    }

    @Override
    public void addAlbum(Long userId, String name, String description, String link) {
        Album album = new Album();
        Optional<Uzer> user = userRepository.findById(userId);
        Optional<Artist> artist = artistRepository.findByUzerId(user.get());
        album.setName(name);
        album.setType("Отсутствуют треки");
        album.setDescription(description);
        album.setLink(link);
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
