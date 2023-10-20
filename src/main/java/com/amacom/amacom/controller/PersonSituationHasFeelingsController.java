package com.amacom.amacom.controller;

import com.amacom.amacom.dto.PersonBabysDTO;
import com.amacom.amacom.dto.PersonSituationHasFeelingsDTO;
import com.amacom.amacom.mapper.PersonBabysMapper;
import com.amacom.amacom.mapper.PersonSituationHasFeelingsMapper;
import com.amacom.amacom.model.PersonBabys;
import com.amacom.amacom.model.PersonSituationHasFeelings;
import com.amacom.amacom.service.interfaces.IFeelingsService;
import com.amacom.amacom.service.interfaces.IPersonSituationHasFeelingsService;
import com.amacom.amacom.service.interfaces.IPersonSituationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/personSituationHasFeelings")
public class PersonSituationHasFeelingsController {

    private IPersonSituationHasFeelingsService personSituationHasFeelingsService;

    private IPersonSituationService personSituationService;

    private IFeelingsService feelingsService;


    @GetMapping("/{id}")
    public ResponseEntity<PersonSituationHasFeelingsDTO> findById(
            @PathVariable(value = "id") UUID id){
        PersonSituationHasFeelings personSituationHasFeelings = this.personSituationHasFeelingsService.findById(id);
        if (personSituationHasFeelings == null) {
            return new ResponseEntity<>(new PersonSituationHasFeelingsDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(PersonSituationHasFeelingsMapper.INSTANCE.toPersonSituationHasFeelingsDTO(personSituationHasFeelings), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<PersonSituationHasFeelingsDTO> create(
            @Valid @RequestBody PersonSituationHasFeelingsDTO personSituationHasFeelingsDTO){

        PersonSituationHasFeelings personSituationHasFeelings = PersonSituationHasFeelingsMapper.INSTANCE.toPersonSituationHasFeelings(personSituationHasFeelingsDTO);

        personSituationHasFeelings.setPersonSituation(this.personSituationService.getEntityFromUUID(personSituationHasFeelingsDTO.getIdPersonSituation()));
        personSituationHasFeelings.setFeelings(this.feelingsService.getEntityFromUUID(personSituationHasFeelingsDTO.getIdFeelings()));

        var personSituationHasFeelingsBD = this.personSituationHasFeelingsService.create(personSituationHasFeelings);
        if(personSituationHasFeelingsBD == null) return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(PersonSituationHasFeelingsMapper.INSTANCE.toPersonSituationHasFeelingsDTO(personSituationHasFeelingsBD));
    }

    @PutMapping
    public ResponseEntity<PersonSituationHasFeelingsDTO> update(
            @Valid @RequestBody PersonSituationHasFeelingsDTO personSituationHasFeelingsDTO){
        PersonSituationHasFeelings personSituationHasFeelings = PersonSituationHasFeelingsMapper.INSTANCE.toPersonSituationHasFeelings(personSituationHasFeelingsDTO);

        personSituationHasFeelings.setPersonSituation(this.personSituationService.getEntityFromUUID(personSituationHasFeelingsDTO.getIdPersonSituation()));
        personSituationHasFeelings.setFeelings(this.feelingsService.getEntityFromUUID(personSituationHasFeelingsDTO.getIdFeelings()));

        var personSituationHasFeelingsBD = this.personSituationHasFeelingsService.update(personSituationHasFeelings);
        if (personSituationHasFeelingsBD == null) {
            return new ResponseEntity<>(new PersonSituationHasFeelingsDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(PersonSituationHasFeelingsMapper.INSTANCE.toPersonSituationHasFeelingsDTO(personSituationHasFeelingsBD), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(
            @PathVariable(value = "id") UUID id){
        this.personSituationHasFeelingsService.deleteById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }


    @Autowired
    public void setPersonSituationService(IPersonSituationService personSituationService) {
        this.personSituationService = personSituationService;
    }

    @Autowired
    public void setFeelingsService(IFeelingsService feelingsService) {
        this.feelingsService = feelingsService;
    }

    @Autowired
    public void setPersonSituationHasFeelingsService(IPersonSituationHasFeelingsService personSituationHasFeelingsService) {
        this.personSituationHasFeelingsService = personSituationHasFeelingsService;
    }

}
