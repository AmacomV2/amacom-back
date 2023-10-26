package com.amacom.amacom.service.interfaces;

import com.amacom.amacom.model.EstadoCivil;
import com.amacom.amacom.model.Event;
import com.amacom.amacom.model.Genero;
import com.amacom.amacom.model.auth.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public interface IEventService {

    Event findById(UUID id);

    Page<Event> findEvent(UUID idCreatedBy, UUID idUsuario, Date fechaDesde, Date fechaHasta, String query, Pageable pageable);

    Event create(Event event);

    Event update(Event event);

    void deleteById(UUID id);

    Event getEntityFromUUID(UUID uuid);
}
