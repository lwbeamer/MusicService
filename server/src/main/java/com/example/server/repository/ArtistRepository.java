package com.example.server.repository;

import com.example.server.entity.Artist;
import com.example.server.entity.Uzer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {
    Optional<Artist> findByUzerId(Uzer userId);

    @Query(value = "SELECT * FROM artist where upper(name) = upper(:name);" ,nativeQuery = true)
    Optional<Artist> findByNameWithoutRegister(@Param("name") String name);
}
