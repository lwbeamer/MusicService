package com.example.server.repository;

import com.example.server.entity.Album;
import com.example.server.entity.UserAlbums;
import com.example.server.entity.Uzer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserAlbumsRepository extends JpaRepository<UserAlbums, Long> {
    Optional<UserAlbums> findByUser(Uzer user);

    @Query(value = "SELECT * FROM (Select * FROM user_albums ORDER BY id DESC LIMIT :count) albums ", nativeQuery = true)
    Optional<List<UserAlbums>> getLastAlbums(@Param("count") Long count);
}
