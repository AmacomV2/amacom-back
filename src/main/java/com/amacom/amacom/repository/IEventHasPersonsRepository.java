package com.amacom.amacom.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.amacom.amacom.model.EventHasPersons;

@Repository
public interface IEventHasPersonsRepository extends JpaRepository<EventHasPersons, UUID> {

        @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END " +
                        "FROM EventHasPersons u " +
                        "WHERE (u.id <> :id or :id is null) " +
                        "AND u.person.id = :personId " +
                        "AND u.event.id = :idEvento ")
        Boolean existsByPersonIdAndIdEvento(UUID id, UUID personId, UUID idEvento);

        @Query("SELECT t " +
                        "FROM EventHasPersons t " +
                        "WHERE (t.event.id = :idEvent )")
        List<EventHasPersons> findAllByIdEvent(UUID idEvent);

}
