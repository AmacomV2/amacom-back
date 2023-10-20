package com.amacom.amacom.service.interfaces;

import com.amacom.amacom.model.Genero;
import com.amacom.amacom.model.PersonSituationHasFeelings;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface IPersonSituationHasFeelingsService {

    PersonSituationHasFeelings findById(UUID id);

    PersonSituationHasFeelings create(PersonSituationHasFeelings personSituationHasFeelings);

    PersonSituationHasFeelings update(PersonSituationHasFeelings personSituationHasFeelings);

    void deleteById(UUID id);

    PersonSituationHasFeelings getEntityFromUUID(UUID uuid);


}
