package com.example.server.repository;

import com.example.server.entity.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrganisationRepository extends JpaRepository<Organisation,Long> {

    @Transactional
    @Modifying
    @Query(value = "SELECT * FROM organisation;",nativeQuery = true)
    Optional<List<Organisation>> getAllOrganisations();
}
