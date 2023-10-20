package com.amacom.amacom.controller;

import com.amacom.amacom.dto.InterventionHasActivitiesDTO;
import com.amacom.amacom.dto.PersonBabysDTO;
import com.amacom.amacom.mapper.InterventionHasActivitiesMapper;
import com.amacom.amacom.mapper.PersonBabysMapper;
import com.amacom.amacom.model.InterventionHasActivities;
import com.amacom.amacom.model.PersonBabys;
import com.amacom.amacom.service.interfaces.IActivityService;
import com.amacom.amacom.service.interfaces.IInterventionHasActivitiesService;
import com.amacom.amacom.service.interfaces.IInterventionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/interventionHasActivities")
public class InterventionHasActivitiesController {

    private IInterventionHasActivitiesService interventionHasActivitiesService;

    private IInterventionService interventionService;

    private IActivityService activityService;


    @GetMapping("/{id}")
    public ResponseEntity<InterventionHasActivitiesDTO> findById(
            @PathVariable(value = "id") UUID id){
        InterventionHasActivities interventionHasActivities = this.interventionHasActivitiesService.findById(id);
        if (interventionHasActivities == null) {
            return new ResponseEntity<>(new InterventionHasActivitiesDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(InterventionHasActivitiesMapper.INSTANCE.toInterventionHasActivitiesDTO(interventionHasActivities), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<InterventionHasActivitiesDTO> create(
            @Valid @RequestBody InterventionHasActivitiesDTO interventionHasActivitiesDTO){

        InterventionHasActivities interventionHasActivities = InterventionHasActivitiesMapper.INSTANCE.toInterventionHasActivities(interventionHasActivitiesDTO);

        interventionHasActivities.setIntervention(this.interventionService.getEntityFromUUID(interventionHasActivitiesDTO.getIdIntervention()));
        interventionHasActivities.setActivity(this.activityService.getEntityFromUUID(interventionHasActivitiesDTO.getIdActivity()));

        var interventionHasActivitiesBD = this.interventionHasActivitiesService.create(interventionHasActivities);
        if(interventionHasActivitiesBD == null) return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(InterventionHasActivitiesMapper.INSTANCE.toInterventionHasActivitiesDTO(interventionHasActivitiesBD));
    }

    @PutMapping
    public ResponseEntity<InterventionHasActivitiesDTO> update(
            @Valid @RequestBody InterventionHasActivitiesDTO interventionHasActivitiesDTO){
        InterventionHasActivities interventionHasActivities = InterventionHasActivitiesMapper.INSTANCE.toInterventionHasActivities(interventionHasActivitiesDTO);

        interventionHasActivities.setIntervention(this.interventionService.getEntityFromUUID(interventionHasActivitiesDTO.getIdIntervention()));
        interventionHasActivities.setActivity(this.activityService.getEntityFromUUID(interventionHasActivitiesDTO.getIdActivity()));

        var interventionHasActivitiesBD = this.interventionHasActivitiesService.update(interventionHasActivities);
        if (interventionHasActivitiesBD == null) {
            return new ResponseEntity<>(new InterventionHasActivitiesDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(InterventionHasActivitiesMapper.INSTANCE.toInterventionHasActivitiesDTO(interventionHasActivitiesBD), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(
            @PathVariable(value = "id") UUID id){
        this.interventionHasActivitiesService.deleteById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }


    @Autowired
    public void setActivityService(IActivityService activityService) {
        this.activityService = activityService;
    }

    @Autowired
    public void setInterventionService(IInterventionService interventionService) {
        this.interventionService = interventionService;
    }

    @Autowired
    public void setInterventionHasActivitiesService(IInterventionHasActivitiesService interventionHasActivitiesService) {
        this.interventionHasActivitiesService = interventionHasActivitiesService;
    }


}
