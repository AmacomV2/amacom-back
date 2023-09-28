package com.amacom.amacom.service.impl;

import com.amacom.amacom.exception.DataNotFoundException;
import com.amacom.amacom.exception.ValidacionException;
import com.amacom.amacom.model.Event;
import com.amacom.amacom.model.EventHasPersons;
import com.amacom.amacom.model.PersonBabys;
import com.amacom.amacom.repository.IEventHasPersonsRepository;
import com.amacom.amacom.repository.IEventRepository;
import com.amacom.amacom.repository.IPersonaRepository;
import com.amacom.amacom.service.interfaces.IEventHasPersonsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Date;

@Service
public class EventHasPersonsServiceImpl implements IEventHasPersonsService {

    private IEventHasPersonsRepository eventHasPersonsRepository;

    private IPersonaRepository personaRepository;

    private IEventRepository eventRepository;

    private EntityManager entityManager;


    @Override
    public EventHasPersons findById(Long id) {
        return this.eventHasPersonsRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Transactional
    @Override
    public EventHasPersons create(EventHasPersons eventHasPersons) {
        this.validarCreacion(eventHasPersons);
        var eventHasPersonsBD = this.eventHasPersonsRepository.save(eventHasPersons);
        this.entityManager.refresh(eventHasPersonsBD);
        return eventHasPersonsBD;
    }

    @Override
    public EventHasPersons update(EventHasPersons eventHasPersons) {
        this.validarCreacion(eventHasPersons);
        var eventHasPersonsBD = this.eventHasPersonsRepository.findById(eventHasPersons.getId()).orElseThrow(DataNotFoundException::new);
        eventHasPersonsBD.setPersona(eventHasPersons.getPersona());
        eventHasPersonsBD.setIdEvento(eventHasPersons.getIdEvento());
        return this.setValoresDTO(eventHasPersonsBD);
    }

    private void validarCreacion(EventHasPersons eventHasPersons){
        var existsSimilar = this.eventHasPersonsRepository.existsByIdPersonaAndIdEvento(eventHasPersons.getId(), eventHasPersons.getPersona().getId(), eventHasPersons.getIdEvento());
        if (Boolean.TRUE.equals(existsSimilar))
            throw new ValidacionException("Ya existe un registro para esta persona y este evento.");
    }

    @Override
    public void deleteById(Long id) {
        var eventHasPersonsBD = this.eventHasPersonsRepository.findById(id).orElseThrow(DataNotFoundException::new);
        this.eventHasPersonsRepository.deleteById(eventHasPersonsBD.getId());
    }

    private EventHasPersons setValoresDTO(EventHasPersons eventHasPersons){

        var eventHasPersonsSaved = this.eventHasPersonsRepository.save(eventHasPersons);
        var event = this.eventRepository.findById(eventHasPersonsSaved.getIdEvento()).orElseThrow(DataNotFoundException::new);
        if(event!= null){
            eventHasPersonsSaved.setEvento(event);
        }
        return eventHasPersonsSaved;
    }

    @Autowired
    public void setPersonaRepository(IPersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
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
