package com.amacom.amacom.repository;

import com.amacom.amacom.model.Persona;
import com.amacom.amacom.model.TipoInstitucion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ITipoInstitucionRepository extends JpaRepository<TipoInstitucion, UUID> {

    @Query("SELECT CASE WHEN COUNT (p) > 0 THEN TRUE ELSE FALSE END " +
            "FROM TipoInstitucion p " +
            "WHERE (p.id <> :id or :id is null) " +
            "AND p.nombre = :nombre ")
    Boolean existsByNombre(UUID id, String nombre);


    @Query("SELECT t " +
            "FROM TipoInstitucion t " +
            "WHERE CONCAT(UPPER(REPLACE(t.nombre, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU')), UPPER(REPLACE(t.descripcion, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU'))) " +
            "LIKE UPPER(CONCAT('%', :query, '%'))")
    Page<TipoInstitucion> findTipoInstitucion(String query, Pageable pageable);

}
