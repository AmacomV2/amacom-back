package com.amacom.amacom.service.interfaces;

import com.amacom.amacom.model.Genero;
import com.amacom.amacom.model.PersonSituationHasAlarmSigns;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface IPersonSituationHasAlarmSignsService {

    PersonSituationHasAlarmSigns findById(UUID id);

    PersonSituationHasAlarmSigns create(PersonSituationHasAlarmSigns personSituationHasAlarmSigns);

    PersonSituationHasAlarmSigns update(PersonSituationHasAlarmSigns personSituationHasAlarmSigns);

    void deleteById(UUID id);

    PersonSituationHasAlarmSigns getEntityFromUUID(UUID uuid);


}
