package com.amacom.amacom.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.amacom.amacom.model.InstitutionService;

@Repository
public interface IInstitutionServiceRepository extends JpaRepository<InstitutionService, UUID> {

        @Query("SELECT t " +
                        "FROM InstitutionService t " +
                        "WHERE (t.institution.id = :idInstitution OR :idInstitution IS NULL) " +
                        "AND (t.services.id = :idService OR :idService IS NULL) " +
                        "AND CONCAT(UPPER(REPLACE(t.institution.name , 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU')), UPPER(REPLACE(t.services.name, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU'))) "
                        +
                        "LIKE UPPER(CONCAT('%', :query, '%'))")
        Page<InstitutionService> findInstitutionService(UUID idInstitution, UUID idService, String query,
                        Pageable pageable);
}
