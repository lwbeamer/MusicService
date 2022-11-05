package com.example.server.repository;

import com.example.server.entity.Uzer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Uzer, Long> {
    Optional<Uzer> findByLogin(String login);

    Boolean existsByLogin(String login);

    @Transactional
    @Modifying
    @Query(value = "CALL add_sub_to_user(:user_id,:sub_id);", nativeQuery = true)
    void addSubToUser(@Param("user_id") Long userId, @Param("sub_id") Long subId);

    @Transactional
    @Query(value = "SELECT EXISTS (SELECT * FROM uzer_albums_songs WHERE  id_user_albums= :idUserAlbums AND id_song = :songId)", nativeQuery = true)
    boolean checkSongInPlaylist(@Param("idUserAlbums") Long idUserAlbums, @Param("songId") Long songId);
    @Transactional
    @Query(value = "SELECT EXISTS (SELECT * FROM user_albums WHERE  id_uzer= :userId)", nativeQuery = true)
    boolean checkExistAlbum(@Param("userId") Long userId);
    @Query(value = "SELECT sub_start FROM uzer where id = :userId ",nativeQuery = true)
    Timestamp getSubStart(@Param("userId") Long userId);
}
