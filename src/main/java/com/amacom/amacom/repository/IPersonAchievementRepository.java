package com.amacom.amacom.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.amacom.amacom.model.PersonAchievement;
import com.amacom.amacom.model.PersonAchievementsScore;

@Repository
public interface IPersonAchievementRepository extends JpaRepository<PersonAchievement, UUID> {

        @Query("SELECT t " +
                        "FROM PersonAchievement t " +
                        "WHERE (t.person.id = :personId OR :personId IS NULL )")
        List<PersonAchievement> findAllByPersonId(UUID personId);

        @Query("SELECT pa " +
                        "FROM PersonAchievement pa " +
                        "WHERE (pa.person.id = :personId AND pa.achievement.id = :achievementId)")
        PersonAchievement searchByProperties(UUID personId, UUID achievementId);

        @Query("SELECT t " +
                        "FROM PersonAchievement t " +
                        "WHERE (t.person.id = :personId OR :personId IS NULL) " +
                        "AND (t.achievement.subject.id = :subjectId OR :subjectId IS NULL) " +
                        "AND CONCAT(UPPER(REPLACE(t.achievement.name , 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU')), UPPER(REPLACE(t.achievement.description, 'áéíóúÁÉÍÓÚ', 'aeiouAEIOU'))) "
                        +
                        "LIKE UPPER(CONCAT('%', :query, '%'))")
        Page<PersonAchievement> findPersonAchievements(UUID personId, UUID subjectId, String query, Pageable pageable);

        @Query("SELECT SUM(pa.score) AS personAchievementCount, pa.achievement.subject.id AS subjectId, "
                        + "pa.achievement.subject.name AS subjectName, COUNT(a) AS achievementCount "
                        +
                        "FROM PersonAchievement pa " +
                        "RIGHT JOIN Achievement a " +
                        "ON a.subject.id = pa.achievement.subject.id " +
                        "GROUP BY pa.achievement.subject.id")
        List<PersonAchievementsScore> getPersonRanking(UUID personId);

}
