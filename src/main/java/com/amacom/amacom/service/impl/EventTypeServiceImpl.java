package com.amacom.amacom.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amacom.amacom.exception.DataNotFoundException;
import com.amacom.amacom.exception.ValidationException;
import com.amacom.amacom.model.EventType;
import com.amacom.amacom.repository.IEventTypeRepository;
import com.amacom.amacom.service.interfaces.IEventTypeService;

@Service
public class EventTypeServiceImpl implements IEventTypeService {

    private IEventTypeRepository eventTypeRepository;

    private EntityManager entityManager;

    @Override
    public EventType getEntityFromUUID(UUID uuid) {
        if (uuid != null) {
            return eventTypeRepository.findById(uuid).orElseThrow(DataNotFoundException::new);
        }
        return null;
    }

    @Override
    public List<EventType> getAll() {
        return this.eventTypeRepository.findAll();
    }

    @Override
    public EventType findById(UUID id) {
        return this.eventTypeRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Transactional
    @Override
    public EventType create(EventType eventType) {
        this.validateCreation(eventType);
        eventType.setId(UUID.randomUUID());
        eventType.setCreatedAt(new Date());
        var eventTypeBD = this.eventTypeRepository.save(eventType);
        this.entityManager.flush();
        this.entityManager.refresh(eventTypeBD);
        return eventTypeBD;
    }

    @Override
    public EventType update(EventType eventType) {
        this.validateCreation(eventType);
        var eventTypeBD = this.eventTypeRepository.findById(eventType.getId())
                .orElseThrow(DataNotFoundException::new);
        eventTypeBD.setName(eventType.getName());
        eventTypeBD.setDescription(eventType.getDescription());
        eventTypeBD.setUpdatedAt(new Date());
        return this.eventTypeRepository.save(eventTypeBD);
    }

    @Override
    public void deleteById(UUID id) {
        var eventTypeBD = this.eventTypeRepository.findById(id).orElseThrow(DataNotFoundException::new);
        this.eventTypeRepository.deleteById(eventTypeBD.getId());
    }

    private void validateCreation(EventType eventType) {

        var existsSimilar = this.eventTypeRepository.existsByNombre(eventType.getId(), eventType.getName());
        if (Boolean.TRUE.equals(existsSimilar))
            throw new ValidationException("Ya existe un registro con este name");
    }

    @Autowired
    public void setEventTypeRepository(IEventTypeRepository eventTypeRepository) {
        this.eventTypeRepository = eventTypeRepository;
    }

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
