package com.example.server.repository;

import com.example.server.entity.Country;
import com.example.server.entity.Role;
import com.example.server.entity.entityhelper.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    Optional<Country> findByName(String name);
}
