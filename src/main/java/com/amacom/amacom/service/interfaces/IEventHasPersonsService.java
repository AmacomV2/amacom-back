package com.amacom.amacom.service.interfaces;

import com.amacom.amacom.model.EventHasPersons;
import com.amacom.amacom.model.PersonBabys;
import org.springframework.stereotype.Service;

@Service
public interface IEventHasPersonsService {

    EventHasPersons findById(Long id);

    EventHasPersons create(EventHasPersons eventHasPersons);

    EventHasPersons update(EventHasPersons eventHasPersons);

    void deleteById(Long id);


}
