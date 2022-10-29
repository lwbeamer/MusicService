package com.example.server.controller;

import com.example.server.dto.SongDTO;
import com.example.server.entity.Song;
import com.example.server.request.CheckSongRequest;
import com.example.server.request.OrgRequest;
import com.example.server.response.MessageResponse;
import com.example.server.service.serviceInterface.AdminServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AdminController {

    @Autowired
    private AdminServiceInterface adminService;

    @PostMapping("/addOrganisation")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addOrganisation(@RequestBody OrgRequest orgRequest) {
        adminService.createOrganisation(orgRequest.getDescription(), orgRequest.getName(), orgRequest.getCountryName());
        return ResponseEntity.ok(new MessageResponse("Организация создана"));
    }

    @GetMapping("/getSongsForAdmin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getSongsForAdmin() {
        return ResponseEntity.ok().body(adminService.getSongsForAdmin());
    }
    @PostMapping("/checkSong")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> checkSong(@RequestBody CheckSongRequest checkSongRequest) {
        adminService.checkSong(checkSongRequest.getUserId(),checkSongRequest.getSongId());
        return ResponseEntity.ok(new MessageResponse("Трек успешно проверен!"));
    }

}
