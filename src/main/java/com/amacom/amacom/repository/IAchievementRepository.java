package com.amacom.amacom.repository;

import com.amacom.amacom.model.Achievement;
import com.amacom.amacom.model.PersonBabys;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IAchievementRepository extends JpaRepository<Achievement, UUID> {

    @Query("SELECT CASE WHEN COUNT (p) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Achievement p " +
            "WHERE (p.id <> :id or :id is null) " +
            "AND p.nombre = :nombre ")
    Boolean existsByNombre(UUID id, String nombre);

    @Query("SELECT t " +
            "FROM Achievement t " +
            "WHERE (t.subject.id = :idSubject OR :idSubject IS NULL) " +
            "AND CONCAT(UPPER(REPLACE(t.nombre , 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU')), UPPER(REPLACE(t.subject.nombre, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU'))) " +
            "LIKE UPPER(CONCAT('%', :query, '%'))")
    Page<Achievement> findAchievement(UUID idSubject, String query, Pageable pageable);

}
