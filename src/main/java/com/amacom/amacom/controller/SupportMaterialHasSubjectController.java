package com.amacom.amacom.controller;

import com.amacom.amacom.dto.PersonBabysDTO;
import com.amacom.amacom.dto.SupportMaterialHasSubjectDTO;
import com.amacom.amacom.mapper.PersonBabysMapper;
import com.amacom.amacom.mapper.SupportMaterialHasSubjectMapper;
import com.amacom.amacom.model.PersonBabys;
import com.amacom.amacom.model.SupportMaterialHasSubject;
import com.amacom.amacom.service.interfaces.ISubjectService;
import com.amacom.amacom.service.interfaces.ISupportMaterialHasSubjectService;
import com.amacom.amacom.service.interfaces.ISupportMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/supportMaterialHasSubject")
public class SupportMaterialHasSubjectController {

    private ISupportMaterialHasSubjectService supportMaterialHasSubjectService;

    private ISupportMaterialService supportMaterialService;

    private ISubjectService subjectService;


    @GetMapping("/{id}")
    public ResponseEntity<SupportMaterialHasSubjectDTO> findById(
            @PathVariable(value = "id") UUID id){
        SupportMaterialHasSubject supportMaterialHasSubject = this.supportMaterialHasSubjectService.findById(id);
        if (supportMaterialHasSubject == null) {
            return new ResponseEntity<>(new SupportMaterialHasSubjectDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(SupportMaterialHasSubjectMapper.INSTANCE.toSupportMaterialHasSubjectDTO(supportMaterialHasSubject), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<SupportMaterialHasSubjectDTO> create(
            @Valid @RequestBody SupportMaterialHasSubjectDTO supportMaterialHasSubjectDTO){

        SupportMaterialHasSubject supportMaterialHasSubject = SupportMaterialHasSubjectMapper.INSTANCE.toSupportMaterialHasSubject(supportMaterialHasSubjectDTO);

        supportMaterialHasSubject.setSupportMaterial(this.supportMaterialService.getEntityFromUUID(supportMaterialHasSubjectDTO.getIdSupportMaterial()));
        supportMaterialHasSubject.setSubject(this.subjectService.getEntityFromUUID(supportMaterialHasSubjectDTO.getIdSubject()));

        var supportMaterialHasSubjectBD = this.supportMaterialHasSubjectService.create(supportMaterialHasSubject);
        if(supportMaterialHasSubjectBD == null) return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(SupportMaterialHasSubjectMapper.INSTANCE.toSupportMaterialHasSubjectDTO(supportMaterialHasSubjectBD));
    }

    @PutMapping
    public ResponseEntity<SupportMaterialHasSubjectDTO> update(
            @Valid @RequestBody SupportMaterialHasSubjectDTO supportMaterialHasSubjectDTO){
        SupportMaterialHasSubject supportMaterialHasSubject = SupportMaterialHasSubjectMapper.INSTANCE.toSupportMaterialHasSubject(supportMaterialHasSubjectDTO);

        supportMaterialHasSubject.setSupportMaterial(this.supportMaterialService.getEntityFromUUID(supportMaterialHasSubjectDTO.getIdSupportMaterial()));
        supportMaterialHasSubject.setSubject(this.subjectService.getEntityFromUUID(supportMaterialHasSubjectDTO.getIdSubject()));

        var supportMaterialHasSubjectBD = this.supportMaterialHasSubjectService.update(supportMaterialHasSubject);
        if (supportMaterialHasSubjectBD == null) {
            return new ResponseEntity<>(new SupportMaterialHasSubjectDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(SupportMaterialHasSubjectMapper.INSTANCE.toSupportMaterialHasSubjectDTO(supportMaterialHasSubjectBD), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(
            @PathVariable(value = "id") UUID id){
        this.supportMaterialHasSubjectService.deleteById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }


    @Autowired
    public void setSubjectService(ISubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Autowired
    public void setSupportMaterialService(ISupportMaterialService supportMaterialService) {
        this.supportMaterialService = supportMaterialService;
    }

    @Autowired
    public void setSupportMaterialHasSubjectService(ISupportMaterialHasSubjectService supportMaterialHasSubjectService) {
        this.supportMaterialHasSubjectService = supportMaterialHasSubjectService;
    }


}
