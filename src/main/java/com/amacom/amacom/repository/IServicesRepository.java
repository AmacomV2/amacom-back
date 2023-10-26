package com.amacom.amacom.repository;

import com.amacom.amacom.model.Persona;
import com.amacom.amacom.model.Services;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface IServicesRepository extends JpaRepository<Services, UUID> {

    @Query("SELECT CASE WHEN COUNT (p) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Services p " +
            "WHERE (p.id <> :id or :id is null) " +
            "AND p.nombre = :nombre ")
    Boolean existsByNombre(UUID id, String nombre);


    @Query("SELECT t " +
            "FROM Services t " +
            "WHERE UPPER(REPLACE(t.nombre, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU')) " +
            "LIKE UPPER(CONCAT('%', :query, '%'))")
    Page<Services> findServices(String query, Pageable pageable);


}
