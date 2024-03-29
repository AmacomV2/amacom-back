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
import org.springframework.web.bind.annotation.RestController;

import com.amacom.amacom.dto.EventTypeDTO;
import com.amacom.amacom.dto.response.ResponseDTO;
import com.amacom.amacom.dto.response.SuccessDTO;
import com.amacom.amacom.mapper.EventTypeMapper;
import com.amacom.amacom.model.EventType;
import com.amacom.amacom.service.interfaces.IEventTypeService;

@RestController
@RequestMapping("/eventType")
public class EventTypeController {

    private IEventTypeService eventTypeService;

    @GetMapping("/getAll")
    public ResponseEntity<ResponseDTO> getAll() {
        List<EventType> eventTypeList = this.eventTypeService.getAll();
        if (eventTypeList == null || eventTypeList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new SuccessDTO(eventTypeList.stream()
                .map(EventTypeMapper.INSTANCE::toEventTypeDTO).collect(Collectors.toList())), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> findById(
            @PathVariable(value = "id") UUID id) {
        EventType eventType = this.eventTypeService.findById(id);
        if (eventType == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new SuccessDTO(EventTypeMapper.INSTANCE.toEventTypeDTO(eventType)), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> create(
            @Valid @RequestBody EventTypeDTO eventTypeDTO) {
        EventType eventType = EventTypeMapper.INSTANCE.toEventType(eventTypeDTO);
        var eventTypeBD = this.eventTypeService.create(eventType);
        if (eventTypeBD == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(new SuccessDTO(EventTypeMapper.INSTANCE.toEventTypeDTO(eventTypeBD)));
    }

    @PutMapping
    public ResponseEntity<ResponseDTO> update(
            @Valid @RequestBody EventTypeDTO eventTypeDTO) {
        EventType eventType = EventTypeMapper.INSTANCE.toEventType(eventTypeDTO);
        var eventTypeBD = this.eventTypeService.update(eventType);
        if (eventTypeBD == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new SuccessDTO(EventTypeMapper.INSTANCE.toEventTypeDTO(eventTypeBD)),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> delete(
            @PathVariable(value = "id") UUID id) {
        this.eventTypeService.deleteById(id);
        return ResponseEntity.ok(new SuccessDTO());
    }

    @Autowired
    public void setEventTypeService(IEventTypeService eventTypeService) {
        this.eventTypeService = eventTypeService;
    }
}
