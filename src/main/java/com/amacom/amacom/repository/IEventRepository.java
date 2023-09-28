package com.amacom.amacom.repository;

import com.amacom.amacom.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IEventRepository extends JpaRepository<Event, UUID> {

    @Query("SELECT CASE WHEN COUNT (e) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Event e " +
            "WHERE (e.id <> :id or :id is null) " +
            "AND e.titulo = :titulo ")
    Boolean existsByTitulo(UUID id, String titulo);


}
