package com.amacom.amacom.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.amacom.amacom.model.Gender;

@Repository
public interface IGeneroRepository extends JpaRepository<Gender, UUID> {

    @Query("SELECT CASE WHEN COUNT (p) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Genero p " +
            "WHERE (p.id <> :id or :id is null) " +
            "AND p.name = :name ")
    Boolean existsByNombre(UUID id, String name);
}
