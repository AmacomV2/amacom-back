package com.amacom.amacom.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.amacom.amacom.model.Achievement;

@Repository
public interface IAchievementRepository extends JpaRepository<Achievement, UUID> {

        @Query("SELECT CASE WHEN COUNT (p) > 0 THEN TRUE ELSE FALSE END " +
                        "FROM Achievement p " +
                        "WHERE (p.id = :id or :id is null) " +
                        "AND p.name = :name ")
        Boolean existByName(UUID id, String name);

        @Query("SELECT t " +
                        "FROM Achievement t " +
                        "WHERE (t.subject.id = :subjectId OR :subjectId IS NULL) " +
                        "AND CONCAT(UPPER(REPLACE(t.name , 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU')), UPPER(REPLACE(t.subject.name, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU'))) "
                        +
                        "LIKE UPPER(CONCAT('%', :query, '%'))")
        Page<Achievement> findAchievement(UUID subjectId, String query, Pageable pageable);

}
