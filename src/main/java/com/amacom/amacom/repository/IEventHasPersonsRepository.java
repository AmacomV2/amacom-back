package com.amacom.amacom.repository;

import com.amacom.amacom.model.EventHasPersons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IEventHasPersonsRepository extends JpaRepository<EventHasPersons,UUID> {

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END " +
            "FROM EventHasPersons u " +
            "WHERE (u.id <> :id or :id is null) " +
            "AND u.persona.id = :idPersona " +
            "AND u.event.id = :idEvento ")
    Boolean existsByIdPersonaAndIdEvento(UUID id, UUID idPersona, UUID idEvento);


}
