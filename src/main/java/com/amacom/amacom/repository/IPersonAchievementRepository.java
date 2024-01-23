package com.amacom.amacom.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

        @Query("SELECT t " +
                        "FROM PersonAchievement t " +
                        "WHERE (t.person.id = :personId OR :personId IS NULL) " +
                        "AND (t.achievement.subject.id = :subjectId OR :subjectId IS NULL) " +
                        "AND CONCAT(UPPER(REPLACE(t.achievement.name , 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU')), UPPER(REPLACE(t.achievement.description, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU'))) "
                        +
                        "LIKE UPPER(CONCAT('%', :query, '%'))")
        Page<PersonAchievement> findPersonAchievements(UUID personId, UUID subjectId, String query, Pageable pageable);

}
