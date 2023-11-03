package com.amacom.amacom.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.amacom.amacom.model.PersonAchievement;

@Repository
public interface IPersonAchievementRepository extends JpaRepository<PersonAchievement, UUID> {

    @Query("SELECT t " +
            "FROM PersonAchievement t " +
            "WHERE (t.person.id = :personId OR :personId IS NULL )")
    List<PersonAchievement> findAllByPersonId(UUID personId);

}
