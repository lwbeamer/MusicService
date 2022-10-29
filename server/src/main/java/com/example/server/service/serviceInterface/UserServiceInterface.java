package com.example.server.service.serviceInterface;

import com.example.server.dto.SongDTO;
import com.example.server.entity.Album;
import com.example.server.entity.Song;
import com.example.server.entity.Subscription;
import com.example.server.entity.Uzer;
import com.example.server.response.FindResponse;

import java.util.List;
import java.util.Optional;

public interface UserServiceInterface {
    void setSub(Optional<Uzer> user, Optional<Subscription> sub);
    void addSongToPlayList(Long userId,Long songId);
    List<SongDTO> getPlayList(Long userId);
    FindResponse findSong(String name);


}
