package com.example.server.service;

import com.example.server.dto.SongDTO;
import com.example.server.entity.*;
import com.example.server.repository.*;
import com.example.server.service.serviceInterface.AdminServiceInterface;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService implements AdminServiceInterface {

    private final OrganisationRepository organisationRepository;
    private final AdminRepository adminRepository;
    private final CountryRepository countryRepository;
    private final UserRepository userRepository;
    private final SongRepository songRepository;

    public AdminService(OrganisationRepository organisationRepository, AdminRepository adminRepository, CountryRepository countryRepository, UserRepository userRepository, SongRepository songRepository) {
        this.organisationRepository = organisationRepository;
        this.adminRepository = adminRepository;
        this.countryRepository = countryRepository;
        this.userRepository = userRepository;
        this.songRepository = songRepository;
    }

    @Override
    public int createOrganisation(String description, String name, String countryName) {
        Optional<Country> country = countryRepository.findByName(countryName);
        Organisation organisation = new Organisation(name, description);
        if (organisationRepository.existsByName(name)){
            return 3;
        }
        if (country.isEmpty()){
            return 2;
        }
        organisation.setCountryId(country.get());
        organisationRepository.save(organisation);
        return 1;
    }

    @Override
    public ArrayList<SongDTO> getSongsForAdmin() {
        Optional<List<Song>> songs = songRepository.getSongsForAdmin();
        ArrayList<SongDTO> songsDTO = new ArrayList<>();
        if (songs.isEmpty()){
            return songsDTO;
        }
        for (Song i : songs.get()) {
            SongDTO son = new SongDTO(i.getId(), i.getName(), i.getLink(), i.getDuration(), i.getAlbumId().getName(), i.getGenreId().getName(),i.getAlbumId().getLink());
            for (Artist k : i.getArtists()) {
                son.getArtistNames().add(k.getName());
            }
            songsDTO.add(son);
        }
        return songsDTO;
    }

    @Override
    public void checkSong(Long userId, Long songId) {
        Optional<Uzer> user = userRepository.findById(userId);
        if (user.isEmpty()){
            return;
        }
        Optional<Admin> admin = adminRepository.findByUzerId(user.get());
        if (admin.isEmpty()){
            return;
        }
        Optional<Song> song = songRepository.findById(songId);
        if (song.isEmpty()){
            return;
        }
        song.get().setAdminId(admin.get());
        songRepository.save(song.get());
    }

    @Override
    public void checkSongReject(Long userId, Long songId) {
        Optional<Uzer> user = userRepository.findById(userId);
        if (user.isEmpty()){
            return;
        }
        Optional<Admin> admin = adminRepository.findByUzerId(user.get());
        if (admin.isEmpty()){
            return;
        }
        Optional<Song> song = songRepository.findById(songId);
        if (song.isEmpty()){
            return;
        }
        songRepository.delete(song.get());
    }
}