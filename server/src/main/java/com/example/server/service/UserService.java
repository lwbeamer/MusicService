package com.example.server.service;

import com.example.server.dto.AlbumDTO;
import com.example.server.dto.SongDTO;
import com.example.server.entity.*;
import com.example.server.repository.*;
import com.example.server.response.FindResponse;
import com.example.server.service.serviceInterface.UserServiceInterface;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceInterface {

    private final UserRepository userRepository;
    private final SongRepository songRepository;
    private final ArtistRepository artistRepository;
    private final AlbumRepository albumRepository;
    private final SubscriptionRepository subscriptionRepository;

    public UserService(UserRepository userRepository, SongRepository songRepository, ArtistRepository artistRepository, AlbumRepository albumRepository, SubscriptionRepository subscriptionRepository) {
        this.userRepository = userRepository;
        this.songRepository = songRepository;
        this.artistRepository = artistRepository;
        this.albumRepository = albumRepository;
        this.subscriptionRepository = subscriptionRepository;
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
                SongDTO son = new SongDTO(i.getId(), i.getName(), i.getLink(), i.getDuration(), i.getAlbumId().getName(), i.getGenreId().getName());
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
        Optional<List<Song>> songs = songRepository.findAllByName(name);
        List<SongDTO> songDTOS = new ArrayList<>();
        if (songs.isPresent()) {
            for (Song i : songs.get()) {
                SongDTO son = new SongDTO(i.getId(), i.getName(), i.getLink(), i.getDuration(), i.getAlbumId().getName(), i.getGenreId().getName());
                son.setArtistNames(new ArrayList<>());
                for (Artist k : i.getArtists()) {
                    son.getArtistNames().add(k.getName());
                }
                songDTOS.add(son);
            }
        }
        Optional<List<Album>> albums = albumRepository.findAllByName(name);
        List<AlbumDTO> albumDTOS = new ArrayList<>();
        if (albums.isPresent()) {
            for (Album i : albums.get()) {
                AlbumDTO albumDTO = new AlbumDTO(i.getId(), i.getType(), i.getName(), i.getDescription());
                albumDTO.setArtistNames(new ArrayList<>());
                for (Artist k : i.getArtists()) {
                    albumDTO.getArtistNames().add(k.getName());
                }
                albumDTOS.add(albumDTO);
            }
        }
        Optional<Artist> artist = artistRepository.findByName(name);
        if (artist.isPresent()) {
            List<Album> albumsArtist = new ArrayList<>(artist.get().getAlbums());
            List<AlbumDTO> albumDTOSArtist = new ArrayList<>();
            for (Album i : albumsArtist) {
                AlbumDTO albumDTO = new AlbumDTO(i.getId(), i.getType(), i.getName(), i.getDescription());
                albumDTO.setArtistNames(new ArrayList<>());
                for (Artist k : i.getArtists()) {
                    albumDTO.getArtistNames().add(k.getName());
                }
                albumDTOSArtist.add(albumDTO);
            }

            List<Song> songsArtist = new ArrayList<>(artist.get().getSongs());
            List<SongDTO> songDTOSArtist = new ArrayList<>();
            for (Song i : songsArtist) {
                SongDTO son = new SongDTO(i.getId(), i.getName(), i.getLink(), i.getDuration(), i.getAlbumId().getName(), i.getGenreId().getName());
                son.setArtistNames(new ArrayList<>());
                for (Artist k : i.getArtists()) {
                    son.getArtistNames().add(k.getName());
                }
                songDTOSArtist.add(son);
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
        return new SongDTO(song.getId(), song.getName(), song.getLink(), song.getDuration(), song.getAlbumId().getName(), song.getGenreId().getName());
    }

}
