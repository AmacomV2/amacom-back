package com.amacom.amacom.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amacom.amacom.dto.EventDTO;
import com.amacom.amacom.dto.response.ErrorDTO;
import com.amacom.amacom.dto.response.ResponseDTO;
import com.amacom.amacom.dto.response.SuccessDTO;
import com.amacom.amacom.mapper.EventMapper;
import com.amacom.amacom.model.Event;
import com.amacom.amacom.model.auth.User;
import com.amacom.amacom.service.interfaces.IEventService;
import com.amacom.amacom.service.interfaces.IEventTypeService;
import com.amacom.amacom.service.interfaces.IPersonService;
import com.amacom.amacom.util.ITools;

@RestController
@RequestMapping("/event")
public class EventController {

    private IEventService eventService;

    private IEventTypeService eventTypeService;

    private IPersonService personService;

    @GetMapping("/search")
    public ResponseEntity<ResponseDTO> findPageable(
            Pageable pageable,
            @RequestParam(name = "idCreatedBy", required = false) UUID idCreatedBy,
            @RequestParam(name = "personId", required = false) UUID personId,
            @RequestParam(name = "from", required = false) @DateTimeFormat(pattern = ITools.PATTERN_DATE) Date from,
            @RequestParam(name = "to", required = false) @DateTimeFormat(pattern = ITools.PATTERN_DATE) Date to,
            @RequestParam(name = "query", required = false) String query) {

        var eventPage = this.eventService.findEvent(idCreatedBy, personId, from, to, query,
                ITools.getPageRequest(pageable, EventMapper.getSortKeys()));

        if (eventPage == null || eventPage.getContent().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new SuccessDTO(eventPage
                .map(EventMapper.INSTANCE::toEventDTO)), HttpStatus.OK);
    }

    @GetMapping("/personEvents")
    public ResponseEntity<ResponseDTO> findPersonEvents(
            @RequestParam(name = "personId", required = false) @Nullable UUID personId,
            @RequestParam(name = "from", required = false) @DateTimeFormat(pattern = ITools.PATTERN_DATE) Date from,
            @RequestParam(name = "to", required = false) @DateTimeFormat(pattern = ITools.PATTERN_DATE) Date to,
            @RequestParam(name = "query", required = false) String query) {
        if (personId == null) {
            var authData = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = User.class.cast(authData);
            personId = user.getPerson().getId();
        }

        try {
            List<Event> eventList = this.eventService.findPersonEvents(personId, from, to, query);
            List<EventDTO> eventListDTO = new ArrayList<>();

            eventList.forEach(t -> {
                var dto = EventMapper.INSTANCE.toEventDTO(t);

                eventListDTO.add(dto);
            });

            return new ResponseEntity<>(new SuccessDTO(eventListDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDTO(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> findById(
            @PathVariable(value = "id") UUID id) {
        Event event = this.eventService.findById(id);
        if (event == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new SuccessDTO(EventMapper.INSTANCE.toEventDTO(event)), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> create(
            @Valid @RequestBody EventDTO eventDTO) {
        var authData = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = User.class.cast(authData);
        if (eventDTO.getPersonId() == null) {
            eventDTO.setPersonId(user.getPerson().getId());
        }
        Event event = EventMapper.INSTANCE.toEvent(eventDTO);

        event.setCreatedBy(user);
        event.setEventType(this.eventTypeService.getEntityFromUUID(eventDTO.getEventTypeId()));
        event.setPerson(this.personService.getPersonFromUUID(eventDTO.getPersonId()));

        var eventBD = this.eventService.create(event);
        if (eventBD == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(new SuccessDTO(EventMapper.INSTANCE.toEventDTO(eventBD)));
    }

    @PutMapping
    public ResponseEntity<ResponseDTO> update(
            @Valid @RequestBody EventDTO eventDTO) {
        Event event = EventMapper.INSTANCE.toEvent(eventDTO);

        event.setEventType(this.eventTypeService.getEntityFromUUID(eventDTO.getEventTypeId()));

        var eventBD = this.eventService.update(event);
        if (eventBD == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(new SuccessDTO(EventMapper.INSTANCE.toEventDTO(eventBD)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> delete(
            @PathVariable(value = "id") UUID id) {
        this.eventService.deleteById(id);
        return ResponseEntity.ok(new SuccessDTO());
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
    public void setPersonService(IPersonService personService) {
        this.personService = personService;
    }

}
