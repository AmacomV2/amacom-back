package com.amacom.amacom.service.interfaces;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.amacom.amacom.model.PersonSituation;

@Service
public interface IPersonSituationService {

    PersonSituation findById(UUID id);

    PersonSituation create(PersonSituation personSituation);

    PersonSituation update(PersonSituation personSituation);

    void deleteById(UUID id);

    PersonSituation getEntityFromUUID(UUID uuid);

}
