package com.example.server.repository;

import com.example.server.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

    @Query(value = "SELECT get_artist_album(:artist_id,:albumName);", nativeQuery = true)
    String getArtistAlbum(@Param("artist_id") Long artistId, @Param("albumName") String albumName);


    @Query(value = "SELECT * FROM (Select * FROM album ORDER BY id DESC LIMIT :count) albums ", nativeQuery = true)
    Optional<List<Album>> getLastAlbums(@Param("count") Long count);

    Optional<Album> findByLink(String link);

    Optional<List<Album>> findAllByName(String name);

    @Query(value = "SELECT * FROM album where upper(name) = upper(:name)", nativeQuery = true)
    Optional<List<Album>> findAllByNameWithoutRegister(@Param("name") String name);

    @Query(value = "SELECT DISTINCT album.id, album.type, album.name, album.description, album.link\n" +
            "FROM album\n" +
            "         INNER JOIN song on album.id = song.id_album\n" +
            "WHERE song.id_genre = :genreId\n" +
            "ORDER BY album.id DESC LIMIT :count\n", nativeQuery = true)
    Optional<List<Album>> findAllAlbumsByGenre(@Param("count") Long count, @Param("genreId") Long genreId);

    @Transactional
    @Query(value = "SELECT * FROM album where id = ANY (SELECT id_album FROM artists_albums where id_artist = :artistId)", nativeQuery = true)
    Optional<List<Album>> getAllAlbumsByArtistId(@Param("artistId") Long artistId);
}
