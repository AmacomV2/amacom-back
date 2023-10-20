package com.amacom.amacom.repository;

import com.amacom.amacom.model.EventHasPersons;
import com.amacom.amacom.model.LogBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IEventHasPersonsRepository extends JpaRepository<EventHasPersons,UUID> {

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END " +
            "FROM EventHasPersons u " +
            "WHERE (u.id <> :id or :id is null) " +
            "AND u.persona.id = :idPersona " +
            "AND u.event.id = :idEvento ")
    Boolean existsByIdPersonaAndIdEvento(UUID id, UUID idPersona, UUID idEvento);


    @Query("SELECT t " +
            "FROM EventHasPersons t " +
            "WHERE (t.event.id = :idEvent )")
    List<EventHasPersons> findAllByIdEvent(UUID idEvent);

}
