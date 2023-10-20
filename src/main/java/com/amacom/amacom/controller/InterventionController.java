package com.amacom.amacom.controller;

import com.amacom.amacom.dto.InterventionDTO;
import com.amacom.amacom.dto.PersonBabysDTO;
import com.amacom.amacom.mapper.InterventionMapper;
import com.amacom.amacom.mapper.PersonBabysMapper;
import com.amacom.amacom.model.Intervention;
import com.amacom.amacom.model.PersonBabys;
import com.amacom.amacom.service.interfaces.IDiagnosisService;
import com.amacom.amacom.service.interfaces.IInterventionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/intervention")
public class InterventionController {

    private IInterventionService interventionService;

    private IDiagnosisService diagnosisService;


    @GetMapping("/{id}")
    public ResponseEntity<InterventionDTO> findById(
            @PathVariable(value = "id") UUID id){
        Intervention intervention = this.interventionService.findById(id);
        if (intervention == null) {
            return new ResponseEntity<>(new InterventionDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(InterventionMapper.INSTANCE.toInterventionDTO(intervention), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<InterventionDTO> create(
            @Valid @RequestBody InterventionDTO interventionDTO){

        Intervention intervention = InterventionMapper.INSTANCE.toIntervention(interventionDTO);

        intervention.setDiagnosis(this.diagnosisService.getEntityFromUUID(interventionDTO.getIdDiagnosis()));

        var interventionBD = this.interventionService.create(intervention);
        if(interventionBD == null) return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(InterventionMapper.INSTANCE.toInterventionDTO(interventionBD));
    }

    @PutMapping
    public ResponseEntity<InterventionDTO> update(
            @Valid @RequestBody InterventionDTO interventionDTO){
        Intervention intervention = InterventionMapper.INSTANCE.toIntervention(interventionDTO);

        intervention.setDiagnosis(this.diagnosisService.getEntityFromUUID(interventionDTO.getIdDiagnosis()));

        var interventionBD = this.interventionService.update(intervention);
        if (interventionBD == null) {
            return new ResponseEntity<>(new InterventionDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(InterventionMapper.INSTANCE.toInterventionDTO(interventionBD), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(
            @PathVariable(value = "id") UUID id){
        this.interventionService.deleteById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }


    @Autowired
    public void setDiagnosisService(IDiagnosisService diagnosisService) {
        this.diagnosisService = diagnosisService;
    }

    @Autowired
    public void setInterventionService(IInterventionService interventionService) {
        this.interventionService = interventionService;
    }


}
