package com.amacom.amacom.controller;

import java.util.UUID;

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

import com.amacom.amacom.dto.SituationTypeDTO;
import com.amacom.amacom.mapper.SituationTypeMapper;
import com.amacom.amacom.model.SituationType;
import com.amacom.amacom.service.interfaces.ISituationTypeService;

@RestController
@RequestMapping("/situationType")
public class SituationTypeController {

    private ISituationTypeService situationTypeService;

    @GetMapping("/{id}")
    public ResponseEntity<SituationTypeDTO> findById(
            @PathVariable(value = "id") UUID id) {
        SituationType situationType = this.situationTypeService.findById(id);
        if (situationType == null) {
            return new ResponseEntity<>(new SituationTypeDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(SituationTypeMapper.INSTANCE.situationTypeDTO(situationType), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<SituationTypeDTO> create(
            @Valid @RequestBody SituationTypeDTO situationTypeDTO) {
        SituationType situationType = SituationTypeMapper.INSTANCE.situationType(situationTypeDTO);
        var situationTypeBD = this.situationTypeService.create(situationType);
        if (situationTypeBD == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(SituationTypeMapper.INSTANCE.situationTypeDTO(situationTypeBD));
    }

    @PutMapping
    public ResponseEntity<SituationTypeDTO> update(
            @Valid @RequestBody SituationTypeDTO situationTypeDTO) {
        SituationType situationType = SituationTypeMapper.INSTANCE.situationType(situationTypeDTO);
        var situationTypeBD = this.situationTypeService.update(situationType);
        if (situationTypeBD == null) {
            return new ResponseEntity<>(new SituationTypeDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(SituationTypeMapper.INSTANCE.situationTypeDTO(situationTypeBD), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(
            @PathVariable(value = "id") UUID id) {
        this.situationTypeService.deleteById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @Autowired
    public void setSituationTypeService(ISituationTypeService situationTypeService) {
        this.situationTypeService = situationTypeService;
    }

}
