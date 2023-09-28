package com.amacom.amacom.service.impl;

import com.amacom.amacom.exception.DataNotFoundException;
import com.amacom.amacom.exception.ValidacionException;
import com.amacom.amacom.model.EstadoCivil;
import com.amacom.amacom.model.Event;
import com.amacom.amacom.model.Genero;
import com.amacom.amacom.model.Persona;
import com.amacom.amacom.repository.IEventRepository;
import com.amacom.amacom.repository.ITipoEventoRepository;
import com.amacom.amacom.service.interfaces.IEventService;
import com.amacom.amacom.util.ITools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.UUID;

@Service
public class EventServiceImpl implements IEventService {

    private IEventRepository eventRepository;

    private EntityManager entityManager;

    private ITipoEventoRepository tipoEventoRepository;


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

    @Transactional
    @Override
    public Event create(Event event) {
        this.validarCreacion(event);
        event.setId(UUID.randomUUID());
        event.setFechaHoraCreacion(new Date());
        var eventBD = this.eventRepository.save(event);
        this.entityManager.flush();
        this.entityManager.refresh(eventBD);
        return eventBD;
    }


    @Override
    public Event update(Event event) {
        this.validarCreacion(event);
        var eventBD = this.eventRepository.findById(event.getId()).orElseThrow(DataNotFoundException::new);
        eventBD.setTipoEvento(event.getTipoEvento());
        eventBD.setTitulo(event.getTitulo());
        eventBD.setDescripcion(event.getDescripcion());
        eventBD.setComienzo(event.getComienzo());
        eventBD.setFin(event.getFin());
        eventBD.setEstadoEvento(event.getEstadoEvento());
        eventBD.setFechaHoraModificacion(new Date());
        return this.eventRepository.save(eventBD);
    }

    @Override
    public void deleteById(UUID id) {
        var eventBD = this.eventRepository.findById(id).orElseThrow(DataNotFoundException::new);
        this.eventRepository.deleteById(eventBD.getId());
    }

    private void validarCreacion(Event event){

        if (event.getFin() != null &&
                ITools.isFechaAMayorIgualQueFechaB(event.getComienzo(),
                        event.getFin(), ">"))
            throw new ValidacionException("La fecha fin es menor que la fecha de comienzo.");


        var existsSimilar = this.eventRepository.existsByTitulo(event.getId(), event.getTitulo());
        if (Boolean.TRUE.equals(existsSimilar))
            throw new ValidacionException("Ya existe un registro con este titulo.");
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
    public void setTipoEventoRepository(ITipoEventoRepository tipoEventoRepository) {
        this.tipoEventoRepository = tipoEventoRepository;
    }
}
