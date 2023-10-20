package com.amacom.amacom.service.interfaces;

import com.amacom.amacom.model.EventHasPersons;
import com.amacom.amacom.model.Genero;
import com.amacom.amacom.model.PersonAchievement;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface IPersonAchievementService {

    PersonAchievement findById(UUID id);

    List<PersonAchievement> getAll(UUID idPersona);

    PersonAchievement create(PersonAchievement personAchievement);

    PersonAchievement update(PersonAchievement personAchievement);

    void deleteById(UUID id);

    PersonAchievement getEntityFromUUID(UUID uuid);


}
