package com.amacom.amacom.repository;

import com.amacom.amacom.model.Diagnosis;
import com.amacom.amacom.model.Feelings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface IDiagnosisRepository extends JpaRepository<Diagnosis, UUID> {

    @Query("SELECT d " +
            "FROM Diagnosis d " +
            "WHERE (d.personSituation.id = :situacionId OR :situacionId IS NULL) " +
            "AND CONCAT(UPPER(REPLACE(d.consultationAlert, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU')), UPPER(REPLACE(d.consultationResult, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU'))," +
            "UPPER(REPLACE(d.consultationStatus, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU')) ) " +
            "LIKE UPPER(CONCAT('%', :query, '%'))")
    Page<Diagnosis> findAllDiagnosisBySituacionId(String query, UUID situacionId, Pageable pageable);
}
