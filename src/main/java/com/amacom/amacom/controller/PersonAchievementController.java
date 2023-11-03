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

import com.amacom.amacom.dto.PersonAchievementDTO;
import com.amacom.amacom.mapper.PersonAchievementMapper;
import com.amacom.amacom.model.PersonAchievement;
import com.amacom.amacom.service.interfaces.IAchievementService;
import com.amacom.amacom.service.interfaces.IPersonAchievementService;
import com.amacom.amacom.service.interfaces.IPersonService;

@RestController
@RequestMapping("/personAchievement")
public class PersonAchievementController {

    private IPersonAchievementService personAchievementService;

    private IPersonService personService;

    private IAchievementService achievementService;

    @GetMapping("/getAll")
    public ResponseEntity<List<PersonAchievementDTO>> getAll(
            @RequestParam(name = "personId", required = false) UUID personId) {
        List<PersonAchievement> personAchievementList = this.personAchievementService.getAll(personId);
        if (personAchievementList == null || personAchievementList.isEmpty()) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(personAchievementList.stream()
                .map(PersonAchievementMapper.INSTANCE::toPersonAchievementDTO).collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonAchievementDTO> findById(
            @PathVariable(value = "id") UUID id) {
        PersonAchievement personAchievement = this.personAchievementService.findById(id);
        if (personAchievement == null) {
            return new ResponseEntity<>(new PersonAchievementDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(PersonAchievementMapper.INSTANCE.toPersonAchievementDTO(personAchievement),
                HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<PersonAchievementDTO> create(
            @Valid @RequestBody PersonAchievementDTO personAchievementDTO) {

        PersonAchievement personAchievement = PersonAchievementMapper.INSTANCE
                .toPersonAchievement(personAchievementDTO);

        personAchievement.setPerson(this.personService.getPersonFromUUID(personAchievementDTO.getPersonId()));
        personAchievement
                .setAchievement(this.achievementService.getEntityFromUUID(personAchievementDTO.getIdAchievement()));

        var personAchievementBD = this.personAchievementService.create(personAchievement);
        if (personAchievementBD == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(PersonAchievementMapper.INSTANCE.toPersonAchievementDTO(personAchievementBD));
    }

    @PutMapping
    public ResponseEntity<PersonAchievementDTO> update(
            @Valid @RequestBody PersonAchievementDTO personAchievementDTO) {
        PersonAchievement personAchievement = PersonAchievementMapper.INSTANCE
                .toPersonAchievement(personAchievementDTO);

        personAchievement.setPerson(this.personService.getPersonFromUUID(personAchievementDTO.getPersonId()));
        personAchievement
                .setAchievement(this.achievementService.getEntityFromUUID(personAchievementDTO.getIdAchievement()));

        var personAchievementBD = this.personAchievementService.update(personAchievement);
        if (personAchievementBD == null) {
            return new ResponseEntity<>(new PersonAchievementDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(PersonAchievementMapper.INSTANCE.toPersonAchievementDTO(personAchievementBD),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(
            @PathVariable(value = "id") UUID id) {
        this.personAchievementService.deleteById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @Autowired
    public void setPersonService(IPersonService personService) {
        this.personService = personService;
    }

    @Autowired
    public void setAchievementService(IAchievementService achievementService) {
        this.achievementService = achievementService;
    }

    @Autowired
    public void setPersonAchievementService(IPersonAchievementService personAchievementService) {
        this.personAchievementService = personAchievementService;
    }

}
