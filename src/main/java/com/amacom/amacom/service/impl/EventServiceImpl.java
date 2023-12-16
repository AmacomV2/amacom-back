package com.amacom.amacom.service.impl;

import java.util.Date;
import java.util.UUID;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amacom.amacom.exception.DataNotFoundException;
import com.amacom.amacom.exception.ValidationException;
import com.amacom.amacom.model.Event;
import com.amacom.amacom.repository.IEventRepository;
import com.amacom.amacom.repository.IEventTypeRepository;
import com.amacom.amacom.service.interfaces.IEventService;
import com.amacom.amacom.util.ITools;

@Service
public class EventServiceImpl implements IEventService {

    private IEventRepository eventRepository;

    private EntityManager entityManager;

    private IEventTypeRepository eventTypeRepository;

    @Override
    public Event getEntityFromUUID(UUID uuid) {
        if (uuid != null) {
            return eventRepository.findById(uuid).orElseThrow(DataNotFoundException::new);
        }
        return null;
    }

    @Override
    public Event findById(UUID id) {
        return this.eventRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Override
    public Page<Event> findEvent(UUID idCreatedBy, UUID userId, Date from, Date to, String query,
            Pageable pageable) {

        Page<Event> eventPage;

        if (pageable.getSort().isUnsorted()) {
            Pageable pageableDefault = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                    Sort.by("name").ascending().and(Sort.by("start").descending()));
            if (idCreatedBy != null) {
                eventPage = this.eventRepository.findEvent(idCreatedBy, from, to, query, pageableDefault);
            } else {
                eventPage = this.eventRepository.findEvent(userId, from, to, query, pageableDefault);
            }
        } else {
            if (idCreatedBy != null) {
                eventPage = this.eventRepository.findEvent(idCreatedBy, from, to, query, pageable);
            } else {
                eventPage = this.eventRepository.findEvent(userId, from, to, query, pageable);
            }
        }
        return eventPage;
    }

    @Transactional
    @Override
    public Event create(Event event) {
        this.validateCreation(event);
        event.setId(UUID.randomUUID());
        event.setCreatedAt(new Date());
        var eventBD = this.eventRepository.save(event);
        this.entityManager.flush();
        this.entityManager.refresh(eventBD);
        return eventBD;
    }

    @Override
    public Event update(Event event) {
        this.validateCreation(event);
        var eventBD = this.eventRepository.findById(event.getId()).orElseThrow(DataNotFoundException::new);
        eventBD.setEventType(event.getEventType());
        eventBD.setName(event.getName());
        eventBD.setDescription(event.getDescription());
        eventBD.setStart(event.getStart());
        eventBD.setEnd(event.getEnd());
        eventBD.setEventStatus(event.getEventStatus());
        eventBD.setUpdatedAt(new Date());
        return this.eventRepository.save(eventBD);
    }

    @Override
    public void deleteById(UUID id) {
        var eventBD = this.eventRepository.findById(id).orElseThrow(DataNotFoundException::new);
        this.eventRepository.deleteById(eventBD.getId());
    }

    private void validateCreation(Event event) {

        if (event.getEnd() != null &&
                ITools.isFechaAMayorIgualQueFechaB(event.getStart(),
                        event.getEnd(), ">"))
            throw new ValidationException("La fecha fin es menor que la fecha de start.");

        var existsSimilar = this.eventRepository.existsByTitle(event.getId(), event.getName());
        if (Boolean.TRUE.equals(existsSimilar))
            throw new ValidationException("Ya existe un registro con este name.");
    }

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Autowired
    public void setEventRepository(IEventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Autowired
    public void setEventTypeRepository(IEventTypeRepository eventTypeRepository) {
        this.eventTypeRepository = eventTypeRepository;
    }
}
