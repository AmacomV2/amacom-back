package com.amacom.amacom.repository;

import com.amacom.amacom.model.EventHasPersons;
import com.amacom.amacom.model.PersonAchievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface IPersonAchievementRepository extends JpaRepository<PersonAchievement, UUID> {

    @Query("SELECT t " +
            "FROM PersonAchievement t " +
            "WHERE (t.persona.id = :idPersona OR :idPersona IS NULL )")
    List<PersonAchievement> findAllByIdPersona(UUID idPersona);

}
