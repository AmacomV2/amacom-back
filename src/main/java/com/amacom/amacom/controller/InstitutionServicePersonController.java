package com.amacom.amacom.controller;

import com.amacom.amacom.dto.InstitutionServicePersonDTO;
import com.amacom.amacom.dto.PersonBabysDTO;
import com.amacom.amacom.mapper.InstitutionServicePersonMapper;
import com.amacom.amacom.mapper.PersonBabysMapper;
import com.amacom.amacom.model.InstitutionServicePerson;
import com.amacom.amacom.model.PersonBabys;
import com.amacom.amacom.service.interfaces.IInstitutionServicePersonService;
import com.amacom.amacom.service.interfaces.IInstitutionServiceService;
import com.amacom.amacom.service.interfaces.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/institutionServicePerson")
public class InstitutionServicePersonController {

    private IInstitutionServicePersonService institutionServicePersonService;

    private IPersonaService personaService;

    private IInstitutionServiceService institutionServiceService;


    @GetMapping("/{id}")
    public ResponseEntity<InstitutionServicePersonDTO> findById(
            @PathVariable(value = "id") UUID id){
        InstitutionServicePerson institutionServicePerson = this.institutionServicePersonService.findById(id);
        if (institutionServicePerson == null) {
            return new ResponseEntity<>(new InstitutionServicePersonDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(InstitutionServicePersonMapper.INSTANCE.toInstitutionServicePersonDTO(institutionServicePerson), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<InstitutionServicePersonDTO> create(
            @Valid @RequestBody InstitutionServicePersonDTO institutionServicePersonDTO){

        InstitutionServicePerson institutionServicePerson = InstitutionServicePersonMapper.INSTANCE.toInstitutionServicePerson(institutionServicePersonDTO);

        institutionServicePerson.setPersona(this.personaService.getPersonaFromUUID(institutionServicePersonDTO.getIdPersona()));
        institutionServicePerson.setInstitutionService(this.institutionServiceService.getEntityFromUUID(institutionServicePersonDTO.getIdInstitutionService()));

        var institutionServicePersonBD = this.institutionServicePersonService.create(institutionServicePerson);
        if(institutionServicePersonBD == null) return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(InstitutionServicePersonMapper.INSTANCE.toInstitutionServicePersonDTO(institutionServicePersonBD));
    }

    @PutMapping
    public ResponseEntity<InstitutionServicePersonDTO> update(
            @Valid @RequestBody InstitutionServicePersonDTO institutionServicePersonDTO){
        InstitutionServicePerson institutionServicePerson = InstitutionServicePersonMapper.INSTANCE.toInstitutionServicePerson(institutionServicePersonDTO);

        institutionServicePerson.setPersona(this.personaService.getPersonaFromUUID(institutionServicePersonDTO.getIdPersona()));
        institutionServicePerson.setInstitutionService(this.institutionServiceService.getEntityFromUUID(institutionServicePersonDTO.getIdInstitutionService()));

        var institutionServicePersonBD = this.institutionServicePersonService.update(institutionServicePerson);
        if (institutionServicePersonBD == null) {
            return new ResponseEntity<>(new InstitutionServicePersonDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(InstitutionServicePersonMapper.INSTANCE.toInstitutionServicePersonDTO(institutionServicePersonBD), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(
            @PathVariable(value = "id") UUID id){
        this.institutionServicePersonService.deleteById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }




    @Autowired
    public void setInstitutionServiceService(IInstitutionServiceService institutionServiceService) {
        this.institutionServiceService = institutionServiceService;
    }

    @Autowired
    public void setPersonaService(IPersonaService personaService) {
        this.personaService = personaService;
    }

    @Autowired
    public void setInstitutionServicePersonService(IInstitutionServicePersonService institutionServicePersonService) {
        this.institutionServicePersonService = institutionServicePersonService;
    }


}
