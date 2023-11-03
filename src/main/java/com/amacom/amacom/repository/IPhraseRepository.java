package com.amacom.amacom.repository;

import com.amacom.amacom.model.Phrase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IPhraseRepository extends JpaRepository<Phrase, UUID> {

    @Query("SELECT CASE WHEN COUNT (p) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Phrase p " +
            "WHERE (p.id <> :id or :id is null) " +
            "AND p.name = :name ")
    Boolean existsByNombre(UUID id, String name);

}
