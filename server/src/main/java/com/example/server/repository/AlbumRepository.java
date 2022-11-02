package com.example.server.repository;

import com.example.server.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

    @Query(value = "SELECT get_artist_album(:artist_id,:albumName);",nativeQuery = true)
    String getArtistAlbum(@Param("artist_id") Long artistId, @Param("albumName") String albumName);


    @Query(value = "SELECT * FROM (Select * FROM album ORDER BY id DESC LIMIT :count) albums ",nativeQuery = true)
    Optional<List<Album>> getLastAlbums(@Param("count") Long count);
    Optional<List<Album>> findAllByName(String name);

}
