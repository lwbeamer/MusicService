package com.example.server.service;

import com.example.server.dto.SongDTO;
import com.example.server.entity.*;
import com.example.server.repository.*;
import com.example.server.service.serviceInterface.AdminServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService implements AdminServiceInterface {

    @Autowired
    private OrganisationRepository organisationRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SongRepository songRepository;

    @Override
    public void createOrganisation(String description, String name, String countryName) {
        Optional<Country> country = countryRepository.findByName(countryName);
        Organisation organisation = new Organisation(name, description);
        organisation.setCountryId(country.get());
        organisationRepository.save(organisation);
    }

    @Override
    public ArrayList<SongDTO> getSongsForAdmin() {
        Collection<Song> songs = songRepository.getSongsForAdmin().get();
        ArrayList<SongDTO> songsDTO = new ArrayList<>();
        for (Song i : songs) {
            SongDTO son = new SongDTO(i.getId(), i.getName(), i.getLink(), i.getDuration(), i.getAlbumId().getName(), i.getGenreId().getName());
            for(Artist k : i.getArtists()){
                son.getArtistNames().add(k.getName());
            }
            songsDTO.add(son);
        }
        return songsDTO;
    }

    @Override
    public void checkSong(Long userId, Long songId) {
        Optional<Uzer> user = userRepository.findById(userId);
        Optional<Admin> admin = adminRepository.findByUzerId(user.get());
        Optional<Song> song = songRepository.findById(songId);
        song.get().setAdminId(admin.get());
        songRepository.save(song.get());
    }
}