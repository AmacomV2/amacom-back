package com.amacom.amacom.service.interfaces;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import com.amacom.amacom.model.EAlarmSignType;
import com.amacom.amacom.model.PersonSituationHasAlarmSigns;

@Service
public interface IPersonSituationHasAlarmSignsService {

    PersonSituationHasAlarmSigns findById(UUID id);

    PersonSituationHasAlarmSigns create(PersonSituationHasAlarmSigns personSituationHasAlarmSigns);

    PersonSituationHasAlarmSigns update(PersonSituationHasAlarmSigns personSituationHasAlarmSigns);

    void deleteById(UUID id);

    PersonSituationHasAlarmSigns getEntityFromUUID(UUID uuid);

    Page<PersonSituationHasAlarmSigns> findAlarmSign(@Nullable EAlarmSignType type, String query, Pageable pageable,
            UUID situationId);

}
