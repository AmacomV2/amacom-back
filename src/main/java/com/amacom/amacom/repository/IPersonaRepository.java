package com.amacom.amacom.repository;

import com.amacom.amacom.model.Persona;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IPersonaRepository extends JpaRepository<Persona, UUID> {

    @Query("SELECT CASE WHEN COUNT (p) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Persona p " +
            "WHERE (p.id <> :id or :id is null) " +
            "AND p.documento = :documento ")
    Boolean existsByDocumento(UUID id, String documento);

    @Query("SELECT t " +
            "FROM Persona t " +
            "WHERE CONCAT(UPPER(REPLACE(t.nombreAndApellido, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU')), UPPER(REPLACE(t.documento, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU'))) " +
            "LIKE UPPER(CONCAT('%', :query, '%'))")
    Page<Persona> findPersona(String query, Pageable pageable);

}
