package com.example.server.service.serviceInterface;

import com.example.server.dto.SongDTO;
import com.example.server.entity.Song;

import java.util.List;

public interface AdminServiceInterface {
    void createOrganisation(String description, String name , String countryName);
    List<SongDTO> getSongsForAdmin();
    void checkSong(Long userId,Long songId);
}
