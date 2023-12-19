package com.amacom.amacom.service.interfaces;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.amacom.amacom.model.PersonAchievement;

@Service
public interface IPersonAchievementService {

    PersonAchievement findById(UUID id);

    List<PersonAchievement> getAll(UUID personId);

    PersonAchievement create(PersonAchievement personAchievement);

    PersonAchievement update(PersonAchievement personAchievement);

    void deleteById(UUID id);

    PersonAchievement getEntityFromUUID(UUID uuid);

}
