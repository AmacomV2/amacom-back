package com.amacom.amacom.controller;

import com.amacom.amacom.dto.PersonBabysDTO;
import com.amacom.amacom.dto.PersonSituationHasAlarmSignsDTO;
import com.amacom.amacom.mapper.PersonBabysMapper;
import com.amacom.amacom.mapper.PersonSituationHasAlarmSignsMapper;
import com.amacom.amacom.model.PersonBabys;
import com.amacom.amacom.model.PersonSituationHasAlarmSigns;
import com.amacom.amacom.service.interfaces.IAlarmSignService;
import com.amacom.amacom.service.interfaces.IPersonSituationHasAlarmSignsService;
import com.amacom.amacom.service.interfaces.IPersonSituationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/personSituationHasAlarmSigns")
public class PersonSituationHasAlarmSignsController {

    private IPersonSituationHasAlarmSignsService personSituationHasAlarmSignsService;

    private IPersonSituationService personSituationService;

    private IAlarmSignService alarmSignService;


    @GetMapping("/{id}")
    public ResponseEntity<PersonSituationHasAlarmSignsDTO> findById(
            @PathVariable(value = "id") UUID id){
       PersonSituationHasAlarmSigns personSituationHasAlarmSigns = this.personSituationHasAlarmSignsService.findById(id);
        if (personSituationHasAlarmSigns == null) {
            return new ResponseEntity<>(new PersonSituationHasAlarmSignsDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(PersonSituationHasAlarmSignsMapper.INSTANCE.toPersonSituationHasAlarmSignsDTO(personSituationHasAlarmSigns), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<PersonSituationHasAlarmSignsDTO> create(
            @Valid @RequestBody PersonSituationHasAlarmSignsDTO personSituationHasAlarmSignsDTO){

        PersonSituationHasAlarmSigns personSituationHasAlarmSigns = PersonSituationHasAlarmSignsMapper.INSTANCE.toPersonSituationHasAlarmSigns(personSituationHasAlarmSignsDTO);

        personSituationHasAlarmSigns.setPersonSituation(this.personSituationService.getEntityFromUUID(personSituationHasAlarmSignsDTO.getIdPersonSituation()));
        personSituationHasAlarmSigns.setAlarmSign(this.alarmSignService.getEntityFromUUID(personSituationHasAlarmSignsDTO.getIdAlarmSign()));

        var personSituationHasAlarmSignsBD = this.personSituationHasAlarmSignsService.create(personSituationHasAlarmSigns);
        if(personSituationHasAlarmSignsBD == null) return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(PersonSituationHasAlarmSignsMapper.INSTANCE.toPersonSituationHasAlarmSignsDTO(personSituationHasAlarmSignsBD));
    }

    @PutMapping
    public ResponseEntity<PersonSituationHasAlarmSignsDTO> update(
            @Valid @RequestBody PersonSituationHasAlarmSignsDTO personSituationHasAlarmSignsDTO){
        PersonSituationHasAlarmSigns personSituationHasAlarmSigns = PersonSituationHasAlarmSignsMapper.INSTANCE.toPersonSituationHasAlarmSigns(personSituationHasAlarmSignsDTO);

        personSituationHasAlarmSigns.setPersonSituation(this.personSituationService.getEntityFromUUID(personSituationHasAlarmSignsDTO.getIdPersonSituation()));
        personSituationHasAlarmSigns.setAlarmSign(this.alarmSignService.getEntityFromUUID(personSituationHasAlarmSignsDTO.getIdAlarmSign()));

        var personSituationHasAlarmSignsBD = this.personSituationHasAlarmSignsService.update(personSituationHasAlarmSigns);
        if (personSituationHasAlarmSignsBD == null) {
            return new ResponseEntity<>(new PersonSituationHasAlarmSignsDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(PersonSituationHasAlarmSignsMapper.INSTANCE.toPersonSituationHasAlarmSignsDTO(personSituationHasAlarmSignsBD), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(
            @PathVariable(value = "id") UUID id){
        this.personSituationHasAlarmSignsService.deleteById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }



    @Autowired
    public void setPersonSituationService(IPersonSituationService personSituationService) {
        this.personSituationService = personSituationService;
    }

    @Autowired
    public void setAlarmSignService(IAlarmSignService alarmSignService) {
        this.alarmSignService = alarmSignService;
    }

    @Autowired
    public void setPersonSituationHasAlarmSignsService(IPersonSituationHasAlarmSignsService personSituationHasAlarmSignsService) {
        this.personSituationHasAlarmSignsService = personSituationHasAlarmSignsService;
    }


}
