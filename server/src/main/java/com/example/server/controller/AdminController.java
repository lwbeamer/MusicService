package com.example.server.controller;

import com.example.server.request.CheckSongRequest;
import com.example.server.request.OrgRequest;
import com.example.server.response.MessageResponse;
import com.example.server.service.serviceInterface.AdminServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AdminController {

    private final AdminServiceInterface adminService;

    public AdminController(AdminServiceInterface adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/addOrganisation")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addOrganisation(@RequestBody OrgRequest orgRequest) {
        int status =adminService.createOrganisation(orgRequest.getDescription(), orgRequest.getName(), orgRequest.getCountryName());
        return switch (status) {
            case 1 -> ResponseEntity.ok(new MessageResponse("Организация создана!"));
            case 2 -> ResponseEntity.badRequest().body(new MessageResponse("Такой страны не существует!"));
            case 3 -> ResponseEntity.badRequest().body(new MessageResponse("Организация с таким названием уже существует!"));
            default -> ResponseEntity.badRequest().body(new MessageResponse("Непредвиденная ошибка"));
        };
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
    @PostMapping("/checkSongReject")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> checkSongReject(@RequestBody CheckSongRequest checkSongRequest) {
        adminService.checkSongReject(checkSongRequest.getUserId(),checkSongRequest.getSongId());
        return ResponseEntity.ok(new MessageResponse("Трек не одобрен!"));
    }

}
