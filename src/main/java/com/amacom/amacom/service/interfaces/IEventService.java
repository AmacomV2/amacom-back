package com.amacom.amacom.service.interfaces;

import com.amacom.amacom.model.EstadoCivil;
import com.amacom.amacom.model.Event;
import com.amacom.amacom.model.Genero;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface IEventService {

    Event findById(UUID id);

    Event create(Event event);

    Event update(Event event);

    void deleteById(UUID id);

    Event getEntityFromUUID(UUID uuid);
}
