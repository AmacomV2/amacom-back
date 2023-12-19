package com.amacom.amacom.service.interfaces;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.amacom.amacom.model.EventType;

@Service
public interface IEventTypeService {

    List<EventType> getAll();

    EventType findById(UUID id);

    EventType create(EventType eventType);

    EventType update(EventType eventType);

    void deleteById(UUID id);

    EventType getEntityFromUUID(UUID uuid);
}
