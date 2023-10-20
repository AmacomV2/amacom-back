package com.amacom.amacom.service.interfaces;

import com.amacom.amacom.model.Genero;
import com.amacom.amacom.model.PersonSituation;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface IPersonSituationService {

    PersonSituation findById(UUID id);

    PersonSituation create(PersonSituation personSituation);

    PersonSituation update(PersonSituation personSituation);

    void deleteById(UUID id);

    PersonSituation getEntityFromUUID(UUID uuid);


}
