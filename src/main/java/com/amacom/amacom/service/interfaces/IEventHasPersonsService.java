package com.amacom.amacom.service.interfaces;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.amacom.amacom.model.EventHasPersons;

@Service
public interface IEventHasPersonsService {

    EventHasPersons findById(UUID id);

    List<EventHasPersons> getAll(UUID eventId);

    EventHasPersons create(EventHasPersons eventHasPersons);

    EventHasPersons update(EventHasPersons eventHasPersons);

    void deleteById(UUID id);

    EventHasPersons getEntityFromUUID(UUID uuid);
}
