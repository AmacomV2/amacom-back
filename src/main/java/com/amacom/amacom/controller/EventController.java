package com.amacom.amacom.controller;

import java.util.Date;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amacom.amacom.dto.EventDTO;
import com.amacom.amacom.mapper.EventMapper;
import com.amacom.amacom.model.Event;
import com.amacom.amacom.service.interfaces.IEventService;
import com.amacom.amacom.service.interfaces.IEventTypeService;
import com.amacom.amacom.service.interfaces.IUserService;
import com.amacom.amacom.util.ITools;

@RestController
@RequestMapping("/event")
public class EventController {

    private IEventService eventService;

    private IEventTypeService eventTypeService;

    private IUserService usersService;

    @GetMapping("/consulta")
    public ResponseEntity<Page<EventDTO>> findPageable(
            Pageable pageable,
            @RequestParam(name = "idCreatedBy", required = false) UUID idCreatedBy,
            @RequestHeader("userId") UUID userId,
            @RequestParam(name = "fechaDesde", required = false) Date fechaDesde,
            @RequestParam(name = "fechaHasta", required = false) Date fechaHasta,
            @RequestParam(name = "query", required = false) String query) {

        var eventPage = this.eventService.findEvent(idCreatedBy, userId, fechaDesde, fechaHasta, query,
                ITools.getPageRequest(pageable, EventMapper.getClavesToSort()));

        if (eventPage == null || eventPage.getContent().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(eventPage
                .map(EventMapper.INSTANCE::toEventDTO), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> findById(
            @PathVariable(value = "id") UUID id) {
        Event event = this.eventService.findById(id);
        if (event == null) {
            return new ResponseEntity<>(new EventDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(EventMapper.INSTANCE.toEventDTO(event), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<EventDTO> create(
            @Valid @RequestBody EventDTO eventDTO,
            @RequestHeader("userId") UUID userId) {

        Event event = EventMapper.INSTANCE.toEvent(eventDTO);

        event.setUsuario(this.usersService.getEntityFromUUID(userId));
        event.setEventType(this.eventTypeService.getEntityFromUUID(eventDTO.getEventTypeId()));

        var eventBD = this.eventService.create(event);
        if (eventBD == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(EventMapper.INSTANCE.toEventDTO(eventBD));
    }

    @PutMapping
    public ResponseEntity<EventDTO> update(
            @Valid @RequestBody EventDTO eventDTO) {
        Event event = EventMapper.INSTANCE.toEvent(eventDTO);

        event.setEventType(this.eventTypeService.getEntityFromUUID(eventDTO.getEventTypeId()));

        var eventBD = this.eventService.update(event);
        if (eventBD == null) {
            return new ResponseEntity<>(new EventDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(EventMapper.INSTANCE.toEventDTO(eventBD), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(
            @PathVariable(value = "id") UUID id) {
        this.eventService.deleteById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @Autowired
    public void setEventService(IEventService eventService) {
        this.eventService = eventService;
    }

    @Autowired
    public void setEventTypeService(IEventTypeService eventTypeService) {
        this.eventTypeService = eventTypeService;
    }

    @Autowired
    public void setUsersService(IUserService usersService) {
        this.usersService = usersService;
    }

}
