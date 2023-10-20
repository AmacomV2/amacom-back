package com.amacom.amacom.service.interfaces;

import com.amacom.amacom.model.EstadoCivil;
import com.amacom.amacom.model.EventHasPersons;
import com.amacom.amacom.model.Genero;
import com.amacom.amacom.model.PersonBabys;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface IEventHasPersonsService {

    EventHasPersons findById(UUID id);

    List<EventHasPersons> getAll(UUID idEvent);

    EventHasPersons create(EventHasPersons eventHasPersons);

    EventHasPersons update(EventHasPersons eventHasPersons);

    void deleteById(UUID id);

    EventHasPersons getEntityFromUUID(UUID uuid);
}
