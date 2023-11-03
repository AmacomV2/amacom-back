package com.amacom.amacom.repository;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.amacom.amacom.model.Event;

@Repository
public interface IEventRepository extends JpaRepository<Event, UUID> {

        @Query("SELECT CASE WHEN COUNT (e) > 0 THEN TRUE ELSE FALSE END " +
                        "FROM Event e " +
                        "WHERE (e.id <> :id or :id is null) " +
                        "AND e.title = :title ")
        Boolean existsByTitle(UUID id, String title);

        @Query("SELECT t " +
                        "FROM Event t " +
                        "WHERE (t.usuario.id = :userId OR :userId IS NULL) " +
                        "AND ((:fechaHasta >= t.end  AND :fechaDesde <= t.start) " +
                        "OR (:fechaDesde <= t.start AND :fechaHasta IS NULL) " +
                        "OR (:fechaHasta >= t.end AND :fechaDesde IS NULL) " +
                        "OR (:fechaHasta IS NULL AND :fechaDesde IS NULL)) " +
                        "AND CONCAT(UPPER(REPLACE(t.title, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU')), UPPER(REPLACE(t.eventType.name, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU'))) "
                        +
                        "LIKE UPPER(CONCAT('%', :query, '%'))")
        Page<Event> findEvent(UUID userId, Date fechaDesde, Date fechaHasta, String query, Pageable pageable);
}
