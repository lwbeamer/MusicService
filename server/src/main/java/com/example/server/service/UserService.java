package com.example.server.service;

import com.example.server.dto.AlbumDTO;
import com.example.server.dto.SongDTO;
import com.example.server.entity.*;
import com.example.server.repository.AlbumRepository;
import com.example.server.repository.ArtistRepository;
import com.example.server.repository.SongRepository;
import com.example.server.repository.UserRepository;
import com.example.server.response.FindResponse;
import com.example.server.service.serviceInterface.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SongRepository songRepository;
    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private AlbumRepository albumRepository;

    @Override
    public void setSub(Optional<Uzer> user, Optional<Subscription> sub) {
        userRepository.addSubToUser(user.get().getId(), sub.get().getId());
    }

    @Override
    public void addSongToPlayList(Long userId, Long songId) {
        Optional<Song> song = songRepository.findById(songId);
        Optional<Uzer> user = userRepository.findById(userId);
        user.get().getSongs().add(song.get());
        userRepository.save(user.get());
    }

    @Override
    public List<SongDTO> getPlayList(Long userId) {
        Optional<List<Song>> songs = songRepository.getPlayList(userId);
        List<SongDTO> songDTOS = new ArrayList<>();
        for (Song i : songs.get()) {
            SongDTO son = new SongDTO(i.getId(), i.getName(), i.getLink(), i.getDuration(), i.getAlbumId().getName(), i.getGenreId().getName());
            for (Artist k : i.getArtists()) {
                son.getArtistNames().add(k.getName());
            }
            songDTOS.add(son);
        }
        return songDTOS;
    }

    @Override
    public FindResponse findSong(String name) {
        List<Song> songs = songRepository.findAllByName(name).get();
        List<SongDTO> songDTOS = new ArrayList<>();
        for (Song i : songs) {
            SongDTO son = new SongDTO(i.getId(), i.getName(), i.getLink(), i.getDuration(), i.getAlbumId().getName(), i.getGenreId().getName());
            son.setArtistNames(new ArrayList<String>());
            for (Artist k : i.getArtists()) {
                son.getArtistNames().add(k.getName());
            }
            songDTOS.add(son);
        }

        List<Album> albums = albumRepository.findAllByName(name).get();
        List<AlbumDTO> albumDTOS = new ArrayList<>();
        for (Album i : albums) {
            AlbumDTO albumDTO = new AlbumDTO(i.getId(), i.getType(), i.getName(), i.getDescription());
            albumDTO.setArtistNames(new ArrayList<String>());
            for (Artist k : i.getArtists()) {
                albumDTO.getArtistNames().add(k.getName());
            }
            albumDTOS.add(albumDTO);
        }

        Optional<Artist> artist = artistRepository.findByName(name);
        if (artist.isPresent()) {
            List<Album> albumsArtist = new ArrayList<>(artist.get().getAlbums());
            List<AlbumDTO> albumDTOSArtist = new ArrayList<>();
            for (Album i : albumsArtist) {
                AlbumDTO albumDTO = new AlbumDTO(i.getId(), i.getType(), i.getName(), i.getDescription());
                albumDTO.setArtistNames(new ArrayList<String>());
                for (Artist k : i.getArtists()) {
                    albumDTO.getArtistNames().add(k.getName());
                }
                albumDTOSArtist.add(albumDTO);
            }

            List<Song> songsArtist = new ArrayList<>(artist.get().getSongs());
            List<SongDTO> songDTOSArtist = new ArrayList<>();
            for (Song i : songsArtist) {
                SongDTO son = new SongDTO(i.getId(), i.getName(), i.getLink(), i.getDuration(), i.getAlbumId().getName(), i.getGenreId().getName());
                son.setArtistNames(new ArrayList<String>());
                for (Artist k : i.getArtists()) {
                    son.getArtistNames().add(k.getName());
                }
                songDTOSArtist.add(son);
            }
            FindResponse findResponse = new FindResponse(songDTOS, albumDTOS, albumDTOSArtist, songDTOSArtist);
            return findResponse;
        } else {
            List<SongDTO> songDTOSArtist = new ArrayList<>();
            List<AlbumDTO> albumDTOSArtist = new ArrayList<>();
            FindResponse findResponse = new FindResponse(songDTOS, albumDTOS, albumDTOSArtist, songDTOSArtist);
            return findResponse;
        }


    }


}
