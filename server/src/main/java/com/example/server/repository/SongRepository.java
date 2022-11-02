package com.example.server.repository;

import com.example.server.entity.Album;
import com.example.server.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    @Query(value = "SELECT * FROM SONG WHERE id_admin IS NULL;", nativeQuery = true)
    Optional<List<Song>> getSongsForAdmin();

    @Query(value = """
            SELECT *
            FROM song
            WHERE song.id in (SELECT uzer_play_list.id_song FROM uzer_play_list) AND :userId in (SELECT uzer_play_list.id_uzer FROM uzer_play_list)""", nativeQuery = true)
    Optional<List<Song>> getPlayList(@Param("userId") Long userId);

    Optional<List<Song>> findAllByName(String name);

    Optional<List<Song>> findAllByAlbumId(Album albumId);

}
