package com.example.server.controller;

import com.example.server.request.*;
import com.example.server.response.MessageResponse;
import com.example.server.service.serviceInterface.UserServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    private final UserServiceInterface userService;


    public UserController(UserServiceInterface userService) {
        this.userService = userService;
    }

    @PostMapping("/addSub")
    public ResponseEntity<?> addSubToUser(@RequestBody SubRequest subRequest) {
        userService.setSub(subRequest.getLogin(), subRequest.getSub());
        return ResponseEntity.ok(new MessageResponse("Подписка успешно обновлена!"));
    }


    @PostMapping("/findSongs")
    public ResponseEntity<?> findSongs(@RequestBody FindSongRequest findSongRequest) {
        return ResponseEntity.ok(userService.findSong(findSongRequest.getName()));
    }

    @PostMapping("/getSongById")
    public ResponseEntity<?> getSong(@RequestBody GetSongRequest getSongRequest) {
        return ResponseEntity.ok().body(userService.getSong(getSongRequest.getSongId()));
    }

    @PostMapping("/getSongByAlbumId")
    public ResponseEntity<?> getSOngByID(@RequestBody GetSongByAlbumRequest getSongByAlbumRequest) {
        return ResponseEntity.ok().body(userService.getSongsById(getSongByAlbumRequest.getAlbumId()));
    }

    @PostMapping("/getLastAlbums")
    public ResponseEntity<?> getLastAlbums(@RequestBody GetLastAlbums getLastAlbums) {
        return ResponseEntity.ok().body(userService.getLastAlbums(getLastAlbums.getCount()));
    }

    @PostMapping("/getAlbumById")
    public ResponseEntity<?> getAlbumById(@RequestBody GetAlbumRequest getAlbumRequest) {
        return ResponseEntity.ok().body(userService.getAlbumById(getAlbumRequest.getAlbumId()));
    }

    @PostMapping("/getAlbumBySongId")
    public ResponseEntity<?> getAlbumById(@RequestBody GetAlbumBySongIdRequest getAlbumBySongIdRequest) {
        return ResponseEntity.ok().body(userService.getAlbumBySongID(getAlbumBySongIdRequest.getSongId()));
    }

    @GetMapping("/getAllGenres")
    public ResponseEntity<?> getAllGenres() {
        return ResponseEntity.ok().body(userService.getAllGenres());
    }

    @GetMapping("/getAllCountries")
    public ResponseEntity<?> getAllCountries() {
        return ResponseEntity.ok().body(userService.getAllCountry());
    }

    @PostMapping("/checkSongInPlaylist")
    public ResponseEntity<?> checkSongInPlaylist(@RequestBody CheckSongInPlaylistRequest checkSongInPlaylistRequest) {
        return ResponseEntity.ok().body(userService.checkSongInPlaylist(checkSongInPlaylistRequest.getUserId(), checkSongInPlaylistRequest.getSongId()));
    }

    @PostMapping("/checkSub")
    public ResponseEntity<?> checkSub(@RequestBody CheckSubRequest checkSubRequest) {
        return ResponseEntity.ok().body(userService.checkSubRequest(checkSubRequest.getUserId()));
    }

    @PostMapping("/getAlbumsByGenre")
    public ResponseEntity<?> getAlbumsByGenre(@RequestBody GetAlbumsWithThisGenreRequest getAlbumsWithThisGenreRequest) {
        return ResponseEntity.ok().body(userService.getAlbumsByGenre(getAlbumsWithThisGenreRequest.getCount(), getAlbumsWithThisGenreRequest.getGenre()));
    }

    @PostMapping("/createUserAlbum")
    public ResponseEntity<?> createUserAlbum(@RequestBody CreateUserAlbumRequest createUserAlbumRequest) {
        if (userService.createUserAlbum(createUserAlbumRequest.getImageLink(), createUserAlbumRequest.getName(), createUserAlbumRequest.getUserId())) {
            return ResponseEntity.ok().body("Успешно создан ");
        }
        return ResponseEntity.badRequest().body("Уже есть альбом");
    }

    @PostMapping("/getUserAlbum")
    public ResponseEntity<?> getUserAlbum(@RequestBody GetUserAlbumRequest getUserAlbumRequest) {
        return ResponseEntity.ok().body(userService.getUserAlbum(getUserAlbumRequest.getUserId()));
    }

    @PostMapping("/addSongToUserAlbum")
    public ResponseEntity<?> addSongToUserAlbum(@RequestBody AddSongToUserAlbumRequest addSongToUserAlbumRequest) {
        if (userService.addSongToUserAlbum(addSongToUserAlbumRequest.getSongId(), addSongToUserAlbumRequest.getUserId())) {
            return ResponseEntity.ok().body("Песня успешно добавлена");
        }
        return ResponseEntity.badRequest().body("Песня уже была добавлена!");
    }

    @PostMapping("/getUserAlbumSongs")
    public ResponseEntity<?> getUserAlbumSongs(@RequestBody GetUserAlbumSongsRequest getUserAlbumSongsRequest) {
        return ResponseEntity.ok().body(userService.getUserAlbumSongs(getUserAlbumSongsRequest.getUserId()));
    }

    @PostMapping("/getLastUserAlbums")
    public ResponseEntity<?> getLastUserAlbums(@RequestBody GetLastUserAlbumsRequest getLastUserAlbumsRequest) {
        return ResponseEntity.ok().body(userService.getLastUserAlbums(getLastUserAlbumsRequest.getCount()));
    }

    @PostMapping("/deleteSongFromUserAlbums")
    public ResponseEntity<?> deleteSongFromUserAlbums(@RequestBody DeleteSongFromUserPlaylistRequest deleteSongFromUserPlaylistRequest) {
        userService.deleteSongFromPlaylist(deleteSongFromUserPlaylistRequest.getUserId(), deleteSongFromUserPlaylistRequest.getSongId());
        return ResponseEntity.ok().body("Трек удален");
    }

    @PostMapping("/getArtistByUserId")
    @PreAuthorize("hasRole('ARTIST')")
    public ResponseEntity<?> getArtistByUserId(@RequestBody GetArtistByIdRequest getArtistByIdRequest) {
        return ResponseEntity.ok().body(userService.getArtistById(getArtistByIdRequest.getUserId()));
    }

}
