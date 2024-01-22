package com.amacom.amacom.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.amacom.amacom.model.InterventionHasActivities;

@Repository
public interface IInterventionHasActivitiesRepository extends JpaRepository<InterventionHasActivities, UUID> {
    @Query("SELECT t " +
            "FROM InterventionHasActivities t " +
            "WHERE (t.intervention.id = :interventionId OR :interventionId IS NULL) " +
            "AND (t.status = :status OR :status IS NULL) " +
            "AND UPPER(REPLACE(t.description , 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU')) "
            +
            "LIKE UPPER(CONCAT('%', :query, '%'))")
    Page<InterventionHasActivities> findInterventionActivities(UUID interventionId, String query, boolean status,
            Pageable pageable);
}
