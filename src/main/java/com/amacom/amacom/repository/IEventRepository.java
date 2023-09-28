package com.amacom.amacom.repository;

import com.amacom.amacom.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IEventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT CASE WHEN COUNT (e) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Event e " +
            "WHERE (e.id <> :id or :id is null) " +
            "AND e.titulo = :titulo ")
    Boolean existsByTitulo(Long id, String titulo);


}
