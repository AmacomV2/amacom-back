package com.amacom.amacom.controller;

import com.amacom.amacom.dto.EventDTO;
import com.amacom.amacom.dto.GeneroDTO;
import com.amacom.amacom.mapper.EventMapper;
import com.amacom.amacom.mapper.GeneroMapper;
import com.amacom.amacom.model.Event;
import com.amacom.amacom.model.Genero;
import com.amacom.amacom.service.interfaces.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/event")
public class EventController {

    private IEventService eventService;

    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> findById(
            @PathVariable(value = "id") Long id){
        Event event = this.eventService.findById(id);
        if (event == null) {
            return new ResponseEntity<>(new EventDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(EventMapper.INSTANCE.toEventDTO(event), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<EventDTO> create(
            @Valid @RequestBody EventDTO eventDTO,
            @RequestHeader("idUsuario") Long idUsuario){
        Event event = EventMapper.INSTANCE.toEvent(eventDTO);
        event.setIdUsuarioCrea(idUsuario);
        var eventBD = this.eventService.create(event);
        if(eventBD == null) return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(EventMapper.INSTANCE.toEventDTO(eventBD));
    }

    @PutMapping
    public ResponseEntity<EventDTO> update(
            @Valid @RequestBody EventDTO eventDTO){
        Event event = EventMapper.INSTANCE.toEvent(eventDTO);
        var eventBD = this.eventService.update(event);
        if (eventBD == null) {
            return new ResponseEntity<>(new EventDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(EventMapper.INSTANCE.toEventDTO(eventBD), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(
            @PathVariable(value = "id") Long id){
        this.eventService.deleteById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @Autowired
    public void setEventService(IEventService eventService) {
        this.eventService = eventService;
    }
}
