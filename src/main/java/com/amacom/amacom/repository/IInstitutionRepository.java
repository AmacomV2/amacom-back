package com.amacom.amacom.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.amacom.amacom.model.Institution;

@Repository
public interface IInstitutionRepository extends JpaRepository<Institution, UUID> {

        @Query("SELECT CASE WHEN COUNT (p) > 0 THEN TRUE ELSE FALSE END " +
                        "FROM Institution p " +
                        "WHERE (p.id = :id or :id is null) " +
                        "AND p.name = :name ")
        Boolean existByName(UUID id, String name);

        @Query("SELECT t " +
                        "FROM Institution t " +
                        "WHERE (t.institutionType.id = :institutionTypeId OR :institutionTypeId IS NULL) " +
                        "AND CONCAT(UPPER(REPLACE(t.name , 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU')), UPPER(REPLACE(t.description, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU'))) "
                        +
                        "LIKE UPPER(CONCAT('%', :query, '%'))")
        Page<Institution> findInstitution(UUID institutionTypeId, String query, Pageable pageable);

}
