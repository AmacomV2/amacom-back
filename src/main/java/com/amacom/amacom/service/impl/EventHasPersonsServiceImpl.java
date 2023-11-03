package com.amacom.amacom.service.impl;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amacom.amacom.exception.DataNotFoundException;
import com.amacom.amacom.exception.ValidationException;
import com.amacom.amacom.model.EventHasPersons;
import com.amacom.amacom.repository.IEventHasPersonsRepository;
import com.amacom.amacom.repository.IEventRepository;
import com.amacom.amacom.repository.IPersonRepository;
import com.amacom.amacom.service.interfaces.IEventHasPersonsService;

@Service
public class EventHasPersonsServiceImpl implements IEventHasPersonsService {

    private IEventHasPersonsRepository eventHasPersonsRepository;

    private IPersonRepository personRepository;

    private IEventRepository eventRepository;

    private EntityManager entityManager;

    @Override
    public EventHasPersons getEntityFromUUID(UUID uuid) {
        if (uuid != null) {
            return eventHasPersonsRepository.findById(uuid).orElseThrow(DataNotFoundException::new);
        }
        return null;
    }

    @Override
    public List<EventHasPersons> getAll(UUID idEvent) {
        return this.eventHasPersonsRepository.findAllByIdEvent(idEvent);
    }

    @Override
    public EventHasPersons findById(UUID id) {
        return this.eventHasPersonsRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Transactional
    @Override
    public EventHasPersons create(EventHasPersons eventHasPersons) {
        this.validateCreation(eventHasPersons);
        eventHasPersons.setId(UUID.randomUUID());
        var eventHasPersonsBD = this.eventHasPersonsRepository.save(eventHasPersons);
        this.entityManager.flush();
        this.entityManager.refresh(eventHasPersonsBD);
        return eventHasPersonsBD;
    }

    @Override
    public EventHasPersons update(EventHasPersons eventHasPersons) {
        this.validateCreation(eventHasPersons);
        var eventHasPersonsBD = this.eventHasPersonsRepository.findById(eventHasPersons.getId())
                .orElseThrow(DataNotFoundException::new);
        eventHasPersonsBD.setPerson(eventHasPersons.getPerson());
        eventHasPersonsBD.setEvent(eventHasPersons.getEvent());
        return this.eventHasPersonsRepository.save(eventHasPersonsBD);
    }

    public void validateCreation(EventHasPersons eventHasPersons) {
        var existsSimilar = this.eventHasPersonsRepository.existsByPersonIdAndIdEvento(eventHasPersons.getId(),
                eventHasPersons.getPerson().getId(), eventHasPersons.getEvent().getId());
        if (Boolean.TRUE.equals(existsSimilar))
            throw new ValidationException("Ya existe un registro para esta person y este evento.");
    }

    @Override
    public void deleteById(UUID id) {
        var eventHasPersonsBD = this.eventHasPersonsRepository.findById(id).orElseThrow(DataNotFoundException::new);
        this.eventHasPersonsRepository.deleteById(eventHasPersonsBD.getId());
    }

    @Autowired
    public void setPersonRepository(IPersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Autowired
    public void setEventHasPersonsRepository(IEventHasPersonsRepository eventHasPersonsRepository) {
        this.eventHasPersonsRepository = eventHasPersonsRepository;
    }

    @Autowired
    public void setEventRepository(IEventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
}
