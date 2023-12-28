package com.amacom.amacom.repository;

import java.util.Date;
import java.util.List;
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
                        "AND e.name = :name ")
        Boolean existsByTitle(UUID id, String name);

        @Query("SELECT t " +
                        "FROM Event t " +
                        "WHERE (t.person.id = :userId OR :userId IS NULL) " +
                        "AND ((:to >= t.end  AND :from <= t.start) " +
                        "OR (:from <= t.start AND :to IS NULL) " +
                        "OR (:to >= t.end AND :from IS NULL) " +
                        "OR (:to IS NULL AND :from IS NULL)) " +
                        "AND CONCAT(UPPER(REPLACE(t.name, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU')), UPPER(REPLACE(t.eventType.name, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU'))) "
                        +
                        "LIKE UPPER(CONCAT('%', :query, '%'))")
        Page<Event> findEvent(UUID userId, Date from, Date to, String query, Pageable pageable);

        @Query("SELECT t " +
                        "FROM Event t " +
                        "WHERE (t.person.id = :personId OR :personId IS NULL) " +
                        "AND ((:to >= t.end  AND :from <= t.start) " +
                        "OR (:from <= t.start AND :to IS NULL) " +
                        "OR (:to >= t.end AND :from IS NULL) " +
                        "OR (:to IS NULL AND :from IS NULL)) " +
                        "AND CONCAT(UPPER(REPLACE(t.name, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU')), UPPER(REPLACE(t.eventType.name, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU'))) "
                        +
                        "LIKE UPPER(CONCAT('%', :query, '%'))")
        List<Event> findPersonEvents(UUID personId, Date from, Date to, String query);
}
