package com.amacom.amacom.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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

import com.amacom.amacom.dto.InterventionDTO;
import com.amacom.amacom.dto.response.ErrorDTO;
import com.amacom.amacom.dto.response.ResponseDTO;
import com.amacom.amacom.dto.response.SuccessDTO;
import com.amacom.amacom.mapper.InterventionMapper;
import com.amacom.amacom.model.Intervention;
import com.amacom.amacom.service.interfaces.IDiagnosisService;
import com.amacom.amacom.service.interfaces.IInterventionService;
import com.amacom.amacom.util.ITools;

@RestController
@RequestMapping("/intervention")
public class InterventionController {

    private IInterventionService interventionService;

    private IDiagnosisService diagnosisService;

    @GetMapping("/search")
    public ResponseEntity<ResponseDTO> findPageable(
            Pageable pageable,
            @RequestParam(name = "diagnosisId", required = true) UUID diagnosisId) {

        try {
            var subjectPage = this.interventionService.findIntervention(diagnosisId,
                    ITools.getPageRequest(pageable, InterventionMapper.getSortKeys()));

            return ResponseEntity.ok(new SuccessDTO(subjectPage
                    .map(InterventionMapper.INSTANCE::toInterventionDTO)));
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDTO(e.getLocalizedMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<InterventionDTO> findById(
            @PathVariable(value = "id") UUID id) {
        Intervention intervention = this.interventionService.findById(id);
        if (intervention == null) {
            return new ResponseEntity<>(new InterventionDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(InterventionMapper.INSTANCE.toInterventionDTO(intervention), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<InterventionDTO> create(
            @Valid @RequestBody InterventionDTO interventionDTO) {

        Intervention intervention = InterventionMapper.INSTANCE.toIntervention(interventionDTO);

        intervention.setDiagnosis(this.diagnosisService.getEntityFromUUID(interventionDTO.getDiagnosisId()));

        var interventionBD = this.interventionService.create(intervention);
        if (interventionBD == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(InterventionMapper.INSTANCE.toInterventionDTO(interventionBD));
    }

    @PutMapping
    public ResponseEntity<InterventionDTO> update(
            @Valid @RequestBody InterventionDTO interventionDTO) {
        Intervention intervention = InterventionMapper.INSTANCE.toIntervention(interventionDTO);

        intervention.setDiagnosis(this.diagnosisService.getEntityFromUUID(interventionDTO.getDiagnosisId()));

        var interventionBD = this.interventionService.update(intervention);
        if (interventionBD == null) {
            return new ResponseEntity<>(new InterventionDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(InterventionMapper.INSTANCE.toInterventionDTO(interventionBD), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(
            @PathVariable(value = "id") UUID id) {
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
