package com.amacom.amacom.repository;

import com.amacom.amacom.model.Institution;
import com.amacom.amacom.model.InstitutionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface IInstitutionServiceRepository extends JpaRepository<InstitutionService, UUID> {

    @Query("SELECT t " +
            "FROM InstitutionService t " +
            "WHERE (t.institution.id = :idInstitution OR :idInstitution IS NULL) " +
            "AND (t.services.id = :idService OR :idService IS NULL) " +
            "AND CONCAT(UPPER(REPLACE(t.institution.nombre , 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU')), UPPER(REPLACE(t.services.nombre, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU'))) " +
            "LIKE UPPER(CONCAT('%', :query, '%'))")
    Page<InstitutionService> findInstitutionService(UUID idInstitution, UUID idService, String query, Pageable pageable);
}
