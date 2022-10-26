package com.example.server.repository;

import com.example.server.entity.Artist;
import com.example.server.entity.Uzer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArtistRepository extends JpaRepository<Artist,Long> {
    Optional<Artist> findByUzerId(Uzer userId);
}
