package com.amacom.amacom.repository;

import com.amacom.amacom.model.InstitutionService;
import com.amacom.amacom.model.InstitutionServicePerson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface IInstitutionServicePersonRepository extends JpaRepository<InstitutionServicePerson, UUID> {

    @Query("SELECT t " +
            "FROM InstitutionServicePerson t " +
            "WHERE (t.institutionService.id = :idInstitutionService OR :idInstitutionService IS NULL) " +
            "AND (t.persona.id = :idPersona OR :idPersona IS NULL) " +
            "AND CONCAT(UPPER(REPLACE(t.institutionService.institution.nombre , 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU')), UPPER(REPLACE(t.persona.nombreAndApellido, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU'))) " +
            "LIKE UPPER(CONCAT('%', :query, '%'))")
    Page<InstitutionServicePerson> findInstitutionServicePerson(UUID idInstitutionService, UUID idPersona, String query, Pageable pageable);

}
