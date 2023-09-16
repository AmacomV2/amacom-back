package com.amacom.amacom.repository;

import com.amacom.amacom.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonaRepository extends JpaRepository<Persona,Long> {

    @Query("SELECT CASE WHEN COUNT (p) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Persona p " +
            "WHERE (p.id <> :id or :id is null) " +
            "AND p.documento = :documento ")
    Boolean existsByDocumento(Long id, String documento);

}
