package com.amacom.amacom.controller;

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
import com.amacom.amacom.dto.response.ErrorDTO;
import com.amacom.amacom.dto.response.ResponseDTO;
import com.amacom.amacom.dto.response.SuccessDTO;
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
    public ResponseEntity<ResponseDTO> getAll(
            @RequestParam(name = "eventId") UUID eventId) {
        List<EventHasPersons> eventHasPersonsList = this.eventHasPersonsService.getAll(eventId);
        if (eventHasPersonsList == null || eventHasPersonsList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(new SuccessDTO(eventHasPersonsList.stream()
                .map(EventHasPersonsMapper.INSTANCE::toEventHasPersonsDTO).collect(Collectors.toList())));
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> create(
            @Valid @RequestBody EventHasPersonsDTO eventHasPersonsDTO) {

        EventHasPersons eventHasPersons = EventHasPersonsMapper.INSTANCE.toEventHasPersons(eventHasPersonsDTO);

        eventHasPersons.setPerson(this.personService.getPersonFromUUID(eventHasPersonsDTO.getPersonId()));
        eventHasPersons.setEvent(this.eventService.getEntityFromUUID(eventHasPersonsDTO.getEventId()));

        var eventHasPersonsBD = this.eventHasPersonsService.create(eventHasPersons);
        if (eventHasPersonsBD == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity
                .ok(new SuccessDTO(EventHasPersonsMapper.INSTANCE.toEventHasPersonsDTO(eventHasPersonsBD)));
    }

    @PutMapping
    public ResponseEntity<ResponseDTO> update(
            @Valid @RequestBody EventHasPersonsDTO eventHasPersonsDTO) {

        try {
            EventHasPersons eventHasPersons = EventHasPersonsMapper.INSTANCE.toEventHasPersons(eventHasPersonsDTO);

            eventHasPersons.setPerson(this.personService.getPersonFromUUID(eventHasPersonsDTO.getPersonId()));
            eventHasPersons.setEvent(this.eventService.getEntityFromUUID(eventHasPersonsDTO.getEventId()));

            var eventHasPersonsBD = this.eventHasPersonsService.update(eventHasPersons);

            return ResponseEntity
                    .ok(new SuccessDTO(EventHasPersonsMapper.INSTANCE.toEventHasPersonsDTO(eventHasPersonsBD)));
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDTO(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> delete(
            @PathVariable(value = "id") UUID id) {
        try {
            this.eventHasPersonsService.deleteById(id);
            return ResponseEntity.ok(new SuccessDTO());
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDTO(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
