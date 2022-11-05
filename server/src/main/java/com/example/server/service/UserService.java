package com.example.server.service;

import com.example.server.dto.*;
import com.example.server.entity.*;
import com.example.server.repository.*;
import com.example.server.response.FindResponse;
import com.example.server.service.serviceInterface.UserServiceInterface;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static java.time.ZoneOffset.UTC;

@Service
public class UserService implements UserServiceInterface {

    private final UserRepository userRepository;
    private final SongRepository songRepository;
    private final ArtistRepository artistRepository;
    private final AlbumRepository albumRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final UserAlbumsRepository userAlbumsRepository;
    private final GenreRepository genreRepository;
    private final CountryRepository countryRepository;

    public UserService(UserRepository userRepository, SongRepository songRepository, ArtistRepository artistRepository, AlbumRepository albumRepository, SubscriptionRepository subscriptionRepository, UserAlbumsRepository userAlbumsRepository, GenreRepository genreRepository, CountryRepository countryRepository) {
        this.userRepository = userRepository;
        this.songRepository = songRepository;
        this.artistRepository = artistRepository;
        this.albumRepository = albumRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.userAlbumsRepository = userAlbumsRepository;
        this.genreRepository = genreRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public void setSub(String login, String sub) {
        Optional<Uzer> user = userRepository.findByLogin(login);
        Optional<Subscription> subs = subscriptionRepository.findByName(sub);
        if (user.isPresent() && subs.isPresent()) {
            userRepository.addSubToUser(user.get().getId(), subs.get().getId());
        }
    }

    @Override
    public void addSongToPlayList(Long userId, Long songId) {
        Optional<Song> song = songRepository.findById(songId);
        Optional<Uzer> user = userRepository.findById(userId);
        if (user.isPresent() && song.isPresent()) {
            user.get().getSongs().add(song.get());
            userRepository.save(user.get());
        }
    }

    @Override
    public List<SongDTO> getPlayList(Long userId) {
        Optional<List<Song>> songs = songRepository.getPlayList(userId);
        List<SongDTO> songDTOS = new ArrayList<>();
        if (songs.isPresent()) {
            for (Song i : songs.get()) {
                SongDTO son = new SongDTO(i.getId(), i.getName(), i.getLink(), i.getDuration(), i.getAlbumId().getName(), i.getGenreId().getName(), i.getAlbumId().getLink());
                for (Artist k : i.getArtists()) {
                    son.getArtistNames().add(k.getName());
                }
                songDTOS.add(son);
            }
            return songDTOS;
        }
        return null;
    }

    @Override
    public FindResponse findSong(String name) {
        Optional<List<Song>> songs = songRepository.findAllByNameWithoutRegister(name);
        List<SongDTO> songDTOS = new ArrayList<>();
        if (songs.isPresent()) {
            for (Song i : songs.get()) {
                if (i.getAdminId() != null) {
                    SongDTO son = new SongDTO(i.getId(), i.getName(), i.getLink(), i.getDuration(), i.getAlbumId().getName(), i.getGenreId().getName(), i.getAlbumId().getLink());
                    son.setArtistNames(new ArrayList<>());
                    for (Artist k : i.getArtists()) {
                        son.getArtistNames().add(k.getName());
                    }
                    songDTOS.add(son);
                }
            }
        }
        Optional<List<Album>> albums = albumRepository.findAllByNameWithoutRegister(name);
        List<AlbumDTO> albumDTOS = new ArrayList<>();
        if (albums.isPresent()) {
            for (Album i : albums.get()) {
                AlbumDTO albumDTO = new AlbumDTO(i.getId(), i.getType(), i.getName(), i.getDescription(), i.getLink());
                albumDTO.setArtistNames(new ArrayList<>());
                for (Artist k : i.getArtists()) {
                    albumDTO.getArtistNames().add(k.getName());
                }
                albumDTOS.add(albumDTO);
            }
        }
        Optional<Artist> artist = artistRepository.findByNameWithoutRegister(name);
        if (artist.isPresent()) {
            List<Album> albumsArtist = new ArrayList<>(artist.get().getAlbums());
            List<AlbumDTO> albumDTOSArtist = new ArrayList<>();
            for (Album i : albumsArtist) {
                AlbumDTO albumDTO = new AlbumDTO(i.getId(), i.getType(), i.getName(), i.getDescription(), i.getLink());
                albumDTO.setArtistNames(new ArrayList<>());
                for (Artist k : i.getArtists()) {
                    albumDTO.getArtistNames().add(k.getName());
                }
                albumDTOSArtist.add(albumDTO);
            }

            List<Song> songsArtist = new ArrayList<>(artist.get().getSongs());
            List<SongDTO> songDTOSArtist = new ArrayList<>();
            for (Song i : songsArtist) {
                if (i.getAdminId() != null) {
                    SongDTO son = new SongDTO(i.getId(), i.getName(), i.getLink(), i.getDuration(), i.getAlbumId().getName(), i.getGenreId().getName(), i.getAlbumId().getLink());
                    son.setArtistNames(new ArrayList<>());
                    for (Artist k : i.getArtists()) {
                        son.getArtistNames().add(k.getName());
                    }
                    songDTOSArtist.add(son);
                }
            }
            return new FindResponse(songDTOS, albumDTOS, albumDTOSArtist, songDTOSArtist);
        } else {
            List<SongDTO> songDTOSArtist = new ArrayList<>();
            List<AlbumDTO> albumDTOSArtist = new ArrayList<>();
            return new FindResponse(songDTOS, albumDTOS, albumDTOSArtist, songDTOSArtist);
        }


    }

    @Override
    public SongDTO getSong(Long songId) {
        Song song = songRepository.findById(songId).get();
        SongDTO songDTO = new SongDTO(song.getId(), song.getName(), song.getLink(), song.getDuration(), song.getAlbumId().getName(), song.getGenreId().getName(), song.getAlbumId().getLink());
        songDTO.setArtistNames(new ArrayList<>());
        for (Artist i : song.getArtists()) {
            songDTO.getArtistNames().add(i.getName());
        }
        return songDTO;

    }

    @Override
    public List<SongDTO> getSongsById(Long albumId) {
        Album album = albumRepository.findById(albumId).get();
        List<Song> songs = songRepository.findAllByAlbumId(album).get();
        SongDTO songDTO;
        List<SongDTO> songDTOS = new ArrayList<>();
        for (Song i : songs) {
            songDTO = new SongDTO(i.getId(), i.getName(), i.getLink(), i.getDuration(), i.getAlbumId().getName(), i.getGenreId().getName(), i.getAlbumId().getLink());
            songDTO.setArtistNames(new ArrayList<>());
            for (Artist j : i.getArtists()) {
                songDTO.getArtistNames().add(j.getName());
            }
            songDTOS.add(songDTO);
        }
        return songDTOS;

    }

    @Override
    public List<AlbumDTO> getLastAlbums(Long count) {
        List<Album> albums = albumRepository.getLastAlbums(count).get();
        List<AlbumDTO> albumDTOS = new ArrayList<>();
        for (Album i : albums) {
            AlbumDTO albumDTO = new AlbumDTO(i.getId(), i.getType(), i.getName(), i.getDescription(), i.getLink());
            albumDTO.setArtistNames(new ArrayList<>());
            for (Artist k : i.getArtists()) {
                albumDTO.getArtistNames().add(k.getName());
            }
            albumDTOS.add(albumDTO);
        }
        return albumDTOS;
    }

    @Override
    public AlbumDTO getAlbumById(Long id) {
        Album album = albumRepository.findById(id).get();
        AlbumDTO albumDTO = new AlbumDTO(album.getId(), album.getType(), album.getName(), album.getDescription(), album.getLink());
        albumDTO.setArtistNames(new ArrayList<>());
        for (Artist k : album.getArtists()) {
            albumDTO.getArtistNames().add(k.getName());
        }

        return albumDTO;
    }

    @Override
    public AlbumDTO getAlbumBySongID(Long songId) {
        Song song = songRepository.findById(songId).get();
        Album album = song.getAlbumId();
        AlbumDTO albumDTO = new AlbumDTO(album.getId(), album.getType(), album.getName(), album.getDescription(), album.getLink());
        albumDTO.setArtistNames(new ArrayList<>());
        for (Artist k : album.getArtists()) {
            albumDTO.getArtistNames().add(k.getName());
        }
        return albumDTO;
    }

    @Override
    public List<GenreDTO> getAllGenres() {
        List<Genre> genres = genreRepository.findAll();
        List<GenreDTO> genreDTOS = new ArrayList<>();
        GenreDTO genreDTO = null;
        for (Genre i : genres) {
            genreDTO = new GenreDTO(i.getId(), i.getName());
            genreDTOS.add(genreDTO);
        }
        return genreDTOS;
    }

    @Override
    public List<CountryDTO> getAllCountry() {
        List<Country> countries = countryRepository.findAll();
        List<CountryDTO> countryDTOS = new ArrayList<>();
        CountryDTO countryDTO = null;
        for (Country i : countries) {
            countryDTO = new CountryDTO(i.getId(), i.getName());
            countryDTOS.add(countryDTO);
        }
        return countryDTOS;
    }

    @Override
    public boolean checkSongInPlaylist(Long userId, Long songId) {
        Uzer user = userRepository.findById(userId).get();
        UserAlbums userAlbums = userAlbumsRepository.findByUser(user).get();
        return userRepository.checkSongInPlaylist(userAlbums.getId(), songId);
    }

    @Override
    public boolean checkSubRequest(Long userId) {
        Timestamp t = userRepository.getSubStart(userId);
        try {
            OffsetDateTime time = OffsetDateTime.ofInstant(t.toInstant(), ZoneId.of(String.valueOf(UTC)));
            if (OffsetDateTime.now().getDayOfYear() > time.getDayOfYear()) {
                return false;
            }
            return true;
        } catch (NullPointerException e) {
            return false;
        }
    }

    @Override
    public List<AlbumDTO> getAlbumsByGenre(Long count, String genre) {
        Long genreId = genreRepository.findByName(genre).get().getId();
        List<Album> albums = albumRepository.findAllAlbumsByGenre(count, genreId).get();
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

    @Override
    public void createUserAlbum(String imageLink, String name, Long userId) {
        Uzer user = userRepository.findById(userId).get();
        UserAlbums albums = new UserAlbums(name, imageLink, user);
        albums.setSongs(new HashSet<>());
        userAlbumsRepository.save(albums);
    }

    @Override
    public UserAlbumsDTO getUserAlbum(Long userId) {
        Uzer user = userRepository.findById(userId).get();
        UserAlbums userAlbums = userAlbumsRepository.findByUser(user).get();
        return new UserAlbumsDTO(userAlbums.getId(), userAlbums.getImageLink(), userAlbums.getName(), userAlbums.getUser().getId());
    }

    @Override
    public void addSongToUserAlbum(Long songId, Long userId) {
        Uzer user = userRepository.findById(userId).get();
        UserAlbums userAlbums = userAlbumsRepository.findByUser(user).get();
        Song song = songRepository.findById(songId).get();
        userAlbums.getSongs().add(song);
        userAlbumsRepository.save(userAlbums);
    }

    @Override
    public List<SongDTO> getUserAlbumSongs(Long userId) {
        Uzer user = userRepository.findById(userId).get();
        UserAlbums userAlbums = userAlbumsRepository.findByUser(user).get();
        List<SongDTO> songDTOS = new ArrayList<>();
        SongDTO songDTO;
        for (Song i : userAlbums.getSongs()) {
            songDTO = new SongDTO(i.getId(), i.getName(), i.getLink(), i.getDuration(), i.getAlbumId().getName(), i.getGenreId().getName(), i.getAlbumId().getLink());
            songDTO.setArtistNames(new ArrayList<>());
            for (Artist j : i.getArtists()) {
                songDTO.getArtistNames().add(j.getName());
            }
            songDTOS.add(songDTO);
        }
        return songDTOS;
    }

    @Override
    public List<UserAlbumsDTO> getLastUserAlbums(Long count) {
        List<UserAlbums> userAlbums = userAlbumsRepository.getLastAlbums(count).get();
        List<UserAlbumsDTO> albumDTOS = new ArrayList<>();
        UserAlbumsDTO userAlbumsDTO;
        for (UserAlbums i : userAlbums) {
            userAlbumsDTO = new UserAlbumsDTO(i.getId(), i.getImageLink(), i.getName(), i.getUser().getId());
            albumDTOS.add(userAlbumsDTO);
        }
        return albumDTOS;
    }

    @Override
    public void deleteSongFromPlaylist(Long userId, Long songId) {
        Uzer user = userRepository.findById(userId).get();
        UserAlbums userAlbums = userAlbumsRepository.findByUser(user).get();
        userAlbumsRepository.deleteSongFromUserPlaylist(userAlbums.getId(),songId);
    }


}
