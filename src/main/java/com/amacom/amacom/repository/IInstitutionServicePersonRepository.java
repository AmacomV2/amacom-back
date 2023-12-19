package com.amacom.amacom.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.amacom.amacom.model.InstitutionServicePerson;

@Repository
public interface IInstitutionServicePersonRepository extends JpaRepository<InstitutionServicePerson, UUID> {

        @Query("SELECT t " +
                        "FROM InstitutionServicePerson t " +
                        "WHERE (t.institutionService.id = :idInstitutionService OR :idInstitutionService IS NULL) " +
                        "AND (t.person.id = :personId OR :personId IS NULL) " +
                        "AND CONCAT(UPPER(REPLACE(t.institutionService.institution.name , 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU')), UPPER(REPLACE(t.person.name, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU'))) "
                        +
                        "LIKE UPPER(CONCAT('%', :query, '%'))")
        Page<InstitutionServicePerson> findInstitutionServicePerson(UUID idInstitutionService, UUID personId,
                        String query,
                        Pageable pageable);

}
