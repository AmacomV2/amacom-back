package com.amacom.amacom.service.interfaces;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.amacom.amacom.model.Event;

@Service
public interface IEventService {

    Event findById(UUID id);

    Page<Event> findEvent(UUID idCreatedBy, UUID userId, Date from, Date to, String query,
            Pageable pageable);

    Event create(Event event);

    Event update(Event event);

    void deleteById(UUID id);

    Event getEntityFromUUID(UUID uuid);
}
