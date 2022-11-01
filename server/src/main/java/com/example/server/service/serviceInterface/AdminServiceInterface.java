package com.example.server.service.serviceInterface;

import com.example.server.dto.SongDTO;

import java.util.List;

public interface AdminServiceInterface {
    int createOrganisation(String description, String name, String countryName);

    List<SongDTO> getSongsForAdmin();

    void checkSong(Long userId, Long songId);
}
