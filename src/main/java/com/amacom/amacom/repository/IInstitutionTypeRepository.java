package com.amacom.amacom.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.amacom.amacom.model.InstitutionType;

@Repository
public interface IInstitutionTypeRepository extends JpaRepository<InstitutionType, UUID> {

        @Query("SELECT CASE WHEN COUNT (p) > 0 THEN TRUE ELSE FALSE END " +
                        "FROM InstitutionType p " +
                        "WHERE (p.id <> :id or :id is null) " +
                        "AND p.name = :name ")
        Boolean existByName(UUID id, String name);

        @Query("SELECT t " +
                        "FROM InstitutionType t " +
                        "WHERE CONCAT_WS(UPPER(REPLACE(t.name, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU')), UPPER(REPLACE(t.description, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU'))) "
                        +
                        "LIKE UPPER(CONCAT('%', :query, '%'))")
        Page<InstitutionType> findInstitutionType(String query, Pageable pageable);

}
