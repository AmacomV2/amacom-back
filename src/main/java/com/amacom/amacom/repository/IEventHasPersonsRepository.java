package com.amacom.amacom.repository;

import com.amacom.amacom.model.EventHasPersons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IEventHasPersonsRepository extends JpaRepository<EventHasPersons,Long> {

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END " +
            "FROM EventHasPersons u " +
            "WHERE (u.id <> :id or :id is null) " +
            "AND u.persona.id = :idPersona " +
            "AND u.idEvento = :idEvento ")
    Boolean existsByIdPersonaAndIdEvento(Long id, UUID idPersona, Long idEvento);


}
