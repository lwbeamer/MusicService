package com.example.server.repository;

import com.example.server.entity.Uzer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Uzer, Long> {
    Optional<Uzer> findByLogin(String login);

    Boolean existsByLogin(String login);


}
