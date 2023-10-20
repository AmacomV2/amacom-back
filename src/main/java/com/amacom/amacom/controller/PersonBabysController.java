package com.amacom.amacom.controller;


import com.amacom.amacom.dto.LogBookDTO;
import com.amacom.amacom.dto.PersonBabysDTO;
import com.amacom.amacom.exception.ValidacionException;
import com.amacom.amacom.mapper.LogBookMapper;
import com.amacom.amacom.mapper.PersonBabysMapper;
import com.amacom.amacom.model.PersonBabys;
import com.amacom.amacom.model.Persona;
import com.amacom.amacom.service.interfaces.IPersonBabysService;
import com.amacom.amacom.service.interfaces.IPersonaService;
import com.amacom.amacom.util.ITools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/personBabys")
public class PersonBabysController {

    private IPersonBabysService personBabysService;

    private IPersonaService personaService;

    @GetMapping("/consulta")
    public ResponseEntity<Page<PersonBabysDTO>> findPageable(
            Pageable pageable,
            @RequestParam(name = "idPersona", required = false) UUID idPersona,
            @RequestParam(name = "query", required = false) String query) {

        var personBabysPage = this.personBabysService.findPersonBabys(idPersona, query, ITools.getPageRequest(pageable, PersonBabysMapper.getClavesToSort()));

        if (personBabysPage == null || personBabysPage.getContent().isEmpty()) {
            return new ResponseEntity<>( HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(personBabysPage
                .map(PersonBabysMapper.INSTANCE::toPersonBabysDTO), HttpStatus.OK);
    }



    @GetMapping("/{id}")
    public ResponseEntity<PersonBabysDTO> findById(
            @PathVariable(value = "id") UUID id){
        PersonBabys personBabys = this.personBabysService.findById(id);
        if (personBabys == null) {
            return new ResponseEntity<>(new PersonBabysDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(PersonBabysMapper.INSTANCE.toPersonBabysDTO(personBabys), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<PersonBabysDTO> create(
            @Valid @RequestBody PersonBabysDTO personBabysDTO){

        PersonBabys personBabys = PersonBabysMapper.INSTANCE.toPersonBabys(personBabysDTO);

        personBabys.setPadre(this.personaService.getPersonaFromUUID(personBabysDTO.getIdPadre()));
        personBabys.setBebe(this.personaService.getPersonaFromUUID(personBabysDTO.getIdBebe()));

        var personBabysBD = this.personBabysService.create(personBabys);
        if(personBabysBD == null) return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(PersonBabysMapper.INSTANCE.toPersonBabysDTO(personBabysBD));
    }

    @PutMapping
    public ResponseEntity<PersonBabysDTO> update(
            @Valid @RequestBody PersonBabysDTO personBabysDTO){
        PersonBabys personBabys = PersonBabysMapper.INSTANCE.toPersonBabys(personBabysDTO);

        personBabys.setPadre(this.personaService.getPersonaFromUUID(personBabysDTO.getIdPadre()));
        personBabys.setBebe(this.personaService.getPersonaFromUUID(personBabysDTO.getIdBebe()));

        var personBabysBD = this.personBabysService.update(personBabys);
        if (personBabysBD == null) {
            return new ResponseEntity<>(new PersonBabysDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(PersonBabysMapper.INSTANCE.toPersonBabysDTO(personBabysBD), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(
            @PathVariable(value = "id") UUID id){
        this.personBabysService.deleteById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }



    @Autowired
    public void setPersonaService(IPersonaService personaService) {
        this.personaService = personaService;
    }

    @Autowired
    public void setPersonBabysService(IPersonBabysService personBabysService) {
        this.personBabysService = personBabysService;
    }
}
