package com.example.server.repository;

import com.example.server.entity.Album;
import com.example.server.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
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
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM artists_song WHERE id_artist = :artistId and id_song = :songId",nativeQuery = true)
    void deleteArtistSong(@Param("artistId") Long artistId,@Param("songId") Long songId);
}
