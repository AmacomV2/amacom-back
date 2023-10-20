package com.amacom.amacom.repository;

import com.amacom.amacom.model.Feelings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface IFeelingsRepository extends JpaRepository<Feelings, UUID> {

    @Query("SELECT CASE WHEN COUNT (p) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Feelings p " +
            "WHERE (p.id <> :id or :id is null) " +
            "AND p.nombre = :nombre ")
    Boolean existsByNombre(UUID id, String nombre);


}
