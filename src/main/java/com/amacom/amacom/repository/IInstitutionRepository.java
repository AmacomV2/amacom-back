package com.amacom.amacom.repository;

import com.amacom.amacom.model.Institution;
import com.amacom.amacom.model.PersonBabys;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface IInstitutionRepository extends JpaRepository<Institution, UUID> {

    @Query("SELECT CASE WHEN COUNT (p) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Institution p " +
            "WHERE (p.id <> :id or :id is null) " +
            "AND p.nombre = :nombre ")
    Boolean existsByNombre(UUID id, String nombre);


    @Query("SELECT t " +
            "FROM Institution t " +
            "WHERE (t.tipoInstitucion.id = :idTipoInstitucion OR :idTipoInstitucion IS NULL) " +
            "AND CONCAT(UPPER(REPLACE(t.nombre , 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU')), UPPER(REPLACE(t.descripcion, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU'))) " +
            "LIKE UPPER(CONCAT('%', :query, '%'))")
    Page<Institution> findInstitution(UUID idTipoInstitucion, String query, Pageable pageable);


}
