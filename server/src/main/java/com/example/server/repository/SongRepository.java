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
    Optional<List<Song>> findAllByName(String name);

    Optional<Song> findByLink(String link);
    @Query(value = "SELECT * FROM song where upper(name) = upper(:name)", nativeQuery = true)
    Optional<List<Song>> findAllByNameWithoutRegister(@Param("name") String name);

    Optional<List<Song>> findAllByAlbumId(Album albumId);

}
