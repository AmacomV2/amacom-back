package com.amacom.amacom.controller;

import com.amacom.amacom.dto.EventHasPersonsDTO;
import com.amacom.amacom.dto.GeneroDTO;
import com.amacom.amacom.dto.PersonBabysDTO;
import com.amacom.amacom.mapper.EventHasPersonsMapper;
import com.amacom.amacom.mapper.GeneroMapper;
import com.amacom.amacom.mapper.PersonBabysMapper;
import com.amacom.amacom.model.EventHasPersons;
import com.amacom.amacom.model.Genero;
import com.amacom.amacom.model.PersonBabys;
import com.amacom.amacom.service.interfaces.IEventHasPersonsService;
import com.amacom.amacom.service.interfaces.IEventService;
import com.amacom.amacom.service.interfaces.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/eventHasPersons")
public class EventHasPersonsController {

    private IEventHasPersonsService eventHasPersonsService;

    private IPersonaService personaService;

    private IEventService eventService;


    @GetMapping("/getAll")
    public ResponseEntity<List<EventHasPersonsDTO>> getAll(
            @RequestParam(name = "idEvent" ) UUID idEvent
    ){
        List<EventHasPersons> eventHasPersonsList = this.eventHasPersonsService.getAll(idEvent);
        if (eventHasPersonsList == null || eventHasPersonsList.isEmpty()) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(eventHasPersonsList.stream()
                .map(EventHasPersonsMapper.INSTANCE::toEventHasPersonsDTO).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventHasPersonsDTO> findById(
            @PathVariable(value = "id") UUID id){
        EventHasPersons eventHasPersons = this.eventHasPersonsService.findById(id);
        if (eventHasPersons == null) {
            return new ResponseEntity<>(new EventHasPersonsDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(EventHasPersonsMapper.INSTANCE.toEventHasPersonsDTO(eventHasPersons), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<EventHasPersonsDTO> create(
            @Valid @RequestBody EventHasPersonsDTO eventHasPersonsDTO){

        EventHasPersons eventHasPersons = EventHasPersonsMapper.INSTANCE.toEventHasPersons(eventHasPersonsDTO);

        eventHasPersons.setPersona(this.personaService.getPersonaFromUUID(eventHasPersonsDTO.getIdPersona()));
        eventHasPersons.setEvent(this.eventService.getEntityFromUUID(eventHasPersonsDTO.getIdEvent()));

        var eventHasPersonsBD = this.eventHasPersonsService.create(eventHasPersons);
        if(eventHasPersonsBD == null) return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(EventHasPersonsMapper.INSTANCE.toEventHasPersonsDTO(eventHasPersonsBD));
    }

    @PutMapping
    public ResponseEntity<EventHasPersonsDTO> update(
            @Valid @RequestBody EventHasPersonsDTO eventHasPersonsDTO){

        EventHasPersons eventHasPersons = EventHasPersonsMapper.INSTANCE.toEventHasPersons(eventHasPersonsDTO);

        eventHasPersons.setPersona(this.personaService.getPersonaFromUUID(eventHasPersonsDTO.getIdPersona()));
        eventHasPersons.setEvent(this.eventService.getEntityFromUUID(eventHasPersonsDTO.getIdEvent()));

        var eventHasPersonsBD = this.eventHasPersonsService.update(eventHasPersons);
        if (eventHasPersonsBD == null) {
            return new ResponseEntity<>(new EventHasPersonsDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(EventHasPersonsMapper.INSTANCE.toEventHasPersonsDTO(eventHasPersonsBD), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(
            @PathVariable(value = "id") UUID id){
        this.eventHasPersonsService.deleteById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }


    @Autowired
    public void setPersonaService(IPersonaService personaService) {
        this.personaService = personaService;
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
