package com.amacom.amacom.controller;

import com.amacom.amacom.dto.InstitutionServiceDTO;
import com.amacom.amacom.dto.InstitutionServicePersonDTO;
import com.amacom.amacom.dto.PersonBabysDTO;
import com.amacom.amacom.mapper.InstitutionServiceMapper;
import com.amacom.amacom.mapper.InstitutionServicePersonMapper;
import com.amacom.amacom.mapper.PersonBabysMapper;
import com.amacom.amacom.model.InstitutionServicePerson;
import com.amacom.amacom.model.PersonBabys;
import com.amacom.amacom.service.interfaces.IInstitutionServicePersonService;
import com.amacom.amacom.service.interfaces.IInstitutionServiceService;
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
@RequestMapping("/institutionServicePerson")
public class InstitutionServicePersonController {

    private IInstitutionServicePersonService institutionServicePersonService;

    private IPersonaService personaService;

    private IInstitutionServiceService institutionServiceService;


    @GetMapping("/consulta")
    public ResponseEntity<Page<InstitutionServicePersonDTO>> findPageable(
            Pageable pageable,
            @RequestParam(name = "idInstitutionService") UUID idInstitutionService,
            @RequestParam(name = "idPersona", required = false) UUID idPersona,
            @RequestParam(name = "query", required = false) String query) {

        var institutionServicePersonPage = this.institutionServicePersonService.findInstitutionServicePerson(idInstitutionService, idPersona, query, ITools.getPageRequest(pageable, InstitutionServicePersonMapper.getClavesToSort()));

        if (institutionServicePersonPage == null || institutionServicePersonPage.getContent().isEmpty()) {
            return new ResponseEntity<>( HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(institutionServicePersonPage
                .map(InstitutionServicePersonMapper.INSTANCE::toInstitutionServicePersonDTO), HttpStatus.OK);
    }

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
