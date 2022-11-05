package com.example.server.controller;

import com.example.server.request.*;
import com.example.server.response.MessageResponse;
import com.example.server.service.serviceInterface.UserServiceInterface;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/addSongToPlaylist")
    public ResponseEntity<?> addSongToPlayList(@RequestBody CheckSongRequest checkSongRequest) {
        userService.addSongToPlayList(checkSongRequest.getUserId(), checkSongRequest.getSongId());
        return ResponseEntity.ok(new MessageResponse("Песня добавлена в плейлист!"));
    }

    @PostMapping("/getPlayList")
    public ResponseEntity<?> getPlayList(@RequestBody GetPlayListRequest getPlayListRequest) {
        return ResponseEntity.ok().body(userService.getPlayList(getPlayListRequest.getUserId()));
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
        userService.createUserAlbum(createUserAlbumRequest.getImageLink(), createUserAlbumRequest.getName(), createUserAlbumRequest.getUserId());
        return ResponseEntity.ok().body("Успешно создан ");
    }

    @PostMapping("/getUserAlbum")
    public ResponseEntity<?> getUserAlbum(@RequestBody GetUserAlbumRequest getUserAlbumRequest) {
        return ResponseEntity.ok().body(userService.getUserAlbum(getUserAlbumRequest.getUserId()));
    }

    @PostMapping("/addSongToUserAlbum")
    public ResponseEntity<?> addSongToUserAlbum(@RequestBody AddSongToUserAlbumRequest addSongToUserAlbumRequest) {
        userService.addSongToUserAlbum(addSongToUserAlbumRequest.getSongId(), addSongToUserAlbumRequest.getUserId());
        return ResponseEntity.ok().body("Песня успешно добавлена");
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
    public ResponseEntity<?> deleteSongFromUserAlbums(@RequestBody DeleteSongFromUserPlaylistRequest deleteSongFromUserPlaylistRequest){
        userService.deleteSongFromPlaylist(deleteSongFromUserPlaylistRequest.getUserId(),deleteSongFromUserPlaylistRequest.getSongId());
        return ResponseEntity.ok().body("Трек удален");
    }

}
