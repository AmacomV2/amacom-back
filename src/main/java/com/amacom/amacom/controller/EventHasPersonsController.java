package com.amacom.amacom.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amacom.amacom.dto.EventHasPersonsDTO;
import com.amacom.amacom.mapper.EventHasPersonsMapper;
import com.amacom.amacom.model.EventHasPersons;
import com.amacom.amacom.service.interfaces.IEventHasPersonsService;
import com.amacom.amacom.service.interfaces.IEventService;
import com.amacom.amacom.service.interfaces.IPersonService;

@RestController
@RequestMapping("/eventHasPersons")
public class EventHasPersonsController {

    private IEventHasPersonsService eventHasPersonsService;

    private IPersonService personService;

    private IEventService eventService;

    @GetMapping("/getAll")
    public ResponseEntity<List<EventHasPersonsDTO>> getAll(
            @RequestParam(name = "idEvent") UUID idEvent) {
        List<EventHasPersons> eventHasPersonsList = this.eventHasPersonsService.getAll(idEvent);
        if (eventHasPersonsList == null || eventHasPersonsList.isEmpty()) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(eventHasPersonsList.stream()
                .map(EventHasPersonsMapper.INSTANCE::toEventHasPersonsDTO).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventHasPersonsDTO> findById(
            @PathVariable(value = "id") UUID id) {
        EventHasPersons eventHasPersons = this.eventHasPersonsService.findById(id);
        if (eventHasPersons == null) {
            return new ResponseEntity<>(new EventHasPersonsDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(EventHasPersonsMapper.INSTANCE.toEventHasPersonsDTO(eventHasPersons),
                HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<EventHasPersonsDTO> create(
            @Valid @RequestBody EventHasPersonsDTO eventHasPersonsDTO) {

        EventHasPersons eventHasPersons = EventHasPersonsMapper.INSTANCE.toEventHasPersons(eventHasPersonsDTO);

        eventHasPersons.setPerson(this.personService.getPersonFromUUID(eventHasPersonsDTO.getPersonId()));
        eventHasPersons.setEvent(this.eventService.getEntityFromUUID(eventHasPersonsDTO.getIdEvent()));

        var eventHasPersonsBD = this.eventHasPersonsService.create(eventHasPersons);
        if (eventHasPersonsBD == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(EventHasPersonsMapper.INSTANCE.toEventHasPersonsDTO(eventHasPersonsBD));
    }

    @PutMapping
    public ResponseEntity<EventHasPersonsDTO> update(
            @Valid @RequestBody EventHasPersonsDTO eventHasPersonsDTO) {

        EventHasPersons eventHasPersons = EventHasPersonsMapper.INSTANCE.toEventHasPersons(eventHasPersonsDTO);

        eventHasPersons.setPerson(this.personService.getPersonFromUUID(eventHasPersonsDTO.getPersonId()));
        eventHasPersons.setEvent(this.eventService.getEntityFromUUID(eventHasPersonsDTO.getIdEvent()));

        var eventHasPersonsBD = this.eventHasPersonsService.update(eventHasPersons);
        if (eventHasPersonsBD == null) {
            return new ResponseEntity<>(new EventHasPersonsDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(EventHasPersonsMapper.INSTANCE.toEventHasPersonsDTO(eventHasPersonsBD),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(
            @PathVariable(value = "id") UUID id) {
        this.eventHasPersonsService.deleteById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @Autowired
    public void setPersonService(IPersonService personService) {
        this.personService = personService;
    }

    @Autowired
    public void setEventHasPersonsService(IEventHasPersonsService eventHasPersonsService) {
        this.eventHasPersonsService = eventHasPersonsService;
    }

    @Autowired
    public void setEventService(IEventService eventService) {
        this.eventService = eventService;
    }
}
