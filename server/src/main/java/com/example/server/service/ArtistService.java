package com.example.server.service;

import com.example.server.dto.AlbumDTO;
import com.example.server.entity.*;
import com.example.server.entity.entityhelper.ERole;
import com.example.server.repository.*;
import com.example.server.service.serviceInterface.ArtistServiceInterface;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArtistService implements ArtistServiceInterface {

    private final ArtistRepository artistRepository;
    private final AlbumRepository albumRepository;
    private final UserRepository userRepository;
    private final GenreRepository genreRepository;
    private final OrganisationRepository organisationRepository;
    private final SongRepository songRepository;


    public ArtistService(ArtistRepository artistRepository, AlbumRepository albumRepository, UserRepository userRepository, GenreRepository genreRepository, OrganisationRepository organisationRepository, SongRepository songRepository) {
        this.artistRepository = artistRepository;
        this.albumRepository = albumRepository;
        this.userRepository = userRepository;
        this.genreRepository = genreRepository;
        this.organisationRepository = organisationRepository;
        this.songRepository = songRepository;
    }

    @Override
    public boolean addArtist(String description, String login, String name) {
        Optional<Uzer> user = userRepository.findByLogin(login);
        if (user.get().getRole().getName().equals(ERole.ROLE_ARTIST)) {
            Artist artist = new Artist(name, description);
            if (user.isPresent()) {
                artist.setUzerId(user.get());
                artistRepository.save(artist);
            } else {
                System.out.println("Не удалось добавить");
            }
            return true;
        }
        return false;

    }

    @Override
    public List<Organisation> getAllOrganisation() {
        return organisationRepository.getAllOrganisations().get();
    }

    @Override
    public void addAlbum(List<String> artistNames, String name, String description, String link) {
        System.out.println(artistNames.get(0));
        Album album = new Album();
        album.setName(name);
        album.setType("Отсутствуют треки");
        album.setDescription(description);
        album.setLink(link);
        Artist firstArtist = null;
        for (int i = 0; i < artistNames.size(); i++) {
            Artist artist = artistRepository.findByNameWithoutRegister(artistNames.get(i)).get();
            if (i >= 1) {
                artist.getAlbums().add(albumRepository.getTogetherAlbum(firstArtist.getId(), name).get());
            } else {
                artist.getAlbums().add(album);
                firstArtist = artist;
            }
            album.getArtists().add(artist);
            artistRepository.save(artist);
        }
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
    public boolean addSong(List<String> artistNames, List<String> featuresNames, String name, Long duration, String albumName, String genre, String link) {

        Artist artist = artistRepository.findByNameWithoutRegister(artistNames.get(0)).get();
        Album album = albumRepository.findById(Long.parseLong(albumRepository.getArtistAlbum(artist.getId(), albumName))).get();
        Song song = new Song();
        song.setLink(link);
        song.setGenreId(genreRepository.findByName(genre).get());
        song.setName(name);
        song.setDuration(duration);
        song.setLast_change(OffsetDateTime.now());
        song.setAlbumId(album);
        for (int i = 0; i < artistNames.size(); i++) {
            if (!artistRepository.findByNameWithoutRegister(artistNames.get(i)).isPresent()) {
                return false;
            }
        }
        for (int i = 0; i < featuresNames.size(); i++) {
            if (!artistRepository.findByNameWithoutRegister(featuresNames.get(i)).isPresent()) {
                return false;
            }
        }
        for (int i = 0; i < artistNames.size(); i++) {
            artist = artistRepository.findByNameWithoutRegister(artistNames.get(i)).get();
            if (i >= 1) {
                artist.getSongs().add(songRepository.findByLink(link).get());
            } else {
                artist.getSongs().add(song);
            }
            song.getArtists().add(artist);
            artistRepository.save(artist);
        }
        for (int i = 0; i < featuresNames.size(); i++) {
            artist = artistRepository.findByNameWithoutRegister(featuresNames.get(i)).get();
            artist.getSongs().add(songRepository.findByLink(link).get());
            song.getArtists().add(artist);
            artistRepository.save(artist);
        }
        return true;
    }

    @Override
    public List<AlbumDTO> getAllAlbumsByArtistId(Long artistId) {
        List<Album> albums = albumRepository.getAllAlbumsByArtistId(artistId).get();
        List<AlbumDTO> albumDTOS = new ArrayList<>();
        AlbumDTO albumDTO;
        for (Album i : albums) {
            albumDTO = new AlbumDTO(i.getId(), i.getType(), i.getName(), i.getDescription(), i.getLink());
            albumDTO.setArtistNames(new ArrayList<>());
            for (Artist k : i.getArtists()) {
                albumDTO.getArtistNames().add(k.getName());
            }
            albumDTOS.add(albumDTO);
        }
        return albumDTOS;
    }
}
