package com.amacom.amacom.service.interfaces;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.amacom.amacom.model.PersonSituationHasFeelings;

@Service
public interface IPersonSituationHasFeelingsService {

    PersonSituationHasFeelings findById(UUID id);

    PersonSituationHasFeelings create(PersonSituationHasFeelings personSituationHasFeelings);

    PersonSituationHasFeelings update(PersonSituationHasFeelings personSituationHasFeelings);

    void deleteById(UUID id);

    PersonSituationHasFeelings getEntityFromUUID(UUID uuid);

}
