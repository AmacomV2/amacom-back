package com.amacom.amacom.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.amacom.amacom.model.SupportMaterial;

@Repository
public interface ISupportMaterialRepository extends JpaRepository<SupportMaterial, UUID> {

    @Query("SELECT sm " +
            "FROM SupportMaterial sm " +
            "INNER JOIN SupportMaterialHasSubject sms " +
            "WHERE (sms.subject.id = :subjectId OR :subjectId IS NULL) " +
            "AND CONCAT(UPPER(REPLACE(sm.name , 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU')), UPPER(REPLACE(sm.description, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU'))) "
            +
            "LIKE UPPER(CONCAT('%', :query, '%'))")
    Page<SupportMaterial> findSupportMaterial(UUID subjectId, String query, Pageable pageable);

}
