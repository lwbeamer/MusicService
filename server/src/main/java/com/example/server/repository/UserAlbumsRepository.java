package com.example.server.repository;

import com.example.server.entity.Album;
import com.example.server.entity.UserAlbums;
import com.example.server.entity.Uzer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserAlbumsRepository extends JpaRepository<UserAlbums, Long> {
    Optional<UserAlbums> findByUser(Uzer user);

    @Query(value = "SELECT * FROM (Select * FROM user_albums ORDER BY id DESC LIMIT :count) albums ", nativeQuery = true)
    Optional<List<UserAlbums>> getLastAlbums(@Param("count") Long count);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM uzer_albums_songs WHERE id_user_albums = :idUserAlbums and id_song = :songId", nativeQuery = true)
    void deleteSongFromUserPlaylist(@Param("idUserAlbums") Long idUserAlbums, @Param("songId") Long songId);

    @Transactional
    @Query(value = "SELECT EXISTS(SELECT * FROM uzer_albums_songs WHERE id_user_albums = :userAlbumId AND id_song = :songId) ", nativeQuery = true)
    boolean checkIfExistSong(@Param("userAlbumId") Long userAlbumId, @Param("songId") Long songId);
}
