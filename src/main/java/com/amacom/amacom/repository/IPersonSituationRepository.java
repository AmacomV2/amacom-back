package com.amacom.amacom.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import com.amacom.amacom.model.EConsultationAlert;
import com.amacom.amacom.model.EConsultationStatus;
import com.amacom.amacom.model.PersonSituation;

@Repository
public interface IPersonSituationRepository extends JpaRepository<PersonSituation, UUID> {
        @Query("SELECT p " +
                        "FROM PersonSituation p " +
                        "WHERE (p.person.id = :personId OR :personId IS NULL) " +
                        "AND p.deletedAt = NULL " +
                        "AND CONCAT(UPPER(REPLACE(p.description, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU')), UPPER(REPLACE(p.behavior, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU'))) "
                        +
                        "LIKE UPPER(CONCAT('%', :query, '%'))")
        Page<PersonSituation> findPersonSituation(String query, UUID personId,
                        Pageable pageable);

        @Query("SELECT p " +
                        "FROM PersonSituation p " +
                        "WHERE (p.currentDiagnosis IS NOT NULL) " +
                        "AND (p.person.id = :personId OR :personId IS NULL) " +
                        "AND p.deletedAt = NULL " +
                        "AND (p.currentDiagnosis IS NULL OR (p.currentDiagnosis.consultationAlert = :consultationAlert OR :consultationAlert IS NULL "
                        +
                        "OR p.currentDiagnosis.consultationStatus = :consultationStatus OR :consultationStatus IS NULL)) "
                        +
                        "AND CONCAT(UPPER(REPLACE(p.description, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU')), UPPER(REPLACE(p.behavior, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU'))) "
                        +
                        "LIKE UPPER(CONCAT('%', :query, '%'))")
        Page<PersonSituation> findPersonSituationDiagnosis(@Nullable EConsultationAlert consultationAlert,
                        @Nullable EConsultationStatus consultationStatus, String query, UUID personId,
                        Pageable pageable);
}
