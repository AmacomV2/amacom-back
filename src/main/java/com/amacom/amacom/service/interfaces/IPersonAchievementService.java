package com.amacom.amacom.service.interfaces;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.amacom.amacom.model.PersonAchievement;
import com.amacom.amacom.model.PersonAchievementsScore;

@Service
public interface IPersonAchievementService {

    PersonAchievement findById(UUID id);

    List<PersonAchievement> getAll(UUID personId);

    Page<PersonAchievement> search(UUID personId, UUID subjectId, String query, Pageable pageable);

    PersonAchievement searchByProperties(UUID personId, UUID achievementId);

    PersonAchievement create(PersonAchievement personAchievement);

    PersonAchievement update(PersonAchievement personAchievement);

    void deleteById(UUID id);

    PersonAchievement getEntityFromUUID(UUID uuid);

    List<PersonAchievementsScore> getPersonRanking(UUID personId);

}
