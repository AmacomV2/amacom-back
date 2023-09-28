package com.amacom.amacom.service.interfaces;

import com.amacom.amacom.model.Event;
import com.amacom.amacom.model.Genero;
import org.springframework.stereotype.Service;

@Service
public interface IEventService {

    Event findById(Long id);

    Event create(Event event);

    Event update(Event event);

    void deleteById(Long id);


}
