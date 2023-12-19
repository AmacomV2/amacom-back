package com.amacom.amacom.controller;

import java.util.UUID;

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
import org.springframework.web.bind.annotation.RestController;

import com.amacom.amacom.dto.ActivityDTO;
import com.amacom.amacom.mapper.ActivityMapper;
import com.amacom.amacom.model.Activity;
import com.amacom.amacom.service.interfaces.IActivityService;

@RestController
@RequestMapping("/activity")
public class ActivityController {

    private IActivityService activityService;

    @GetMapping("/{id}")
    public ResponseEntity<ActivityDTO> findById(
            @PathVariable(value = "id") UUID id) {
        Activity activity = this.activityService.findById(id);
        if (activity == null) {
            return new ResponseEntity<>(new ActivityDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ActivityMapper.INSTANCE.toActivityDTO(activity), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ActivityDTO> create(
            @Valid @RequestBody ActivityDTO activityDTO) {
        Activity activity = ActivityMapper.INSTANCE.toActivity(activityDTO);
        var activityBD = this.activityService.create(activity);
        if (activityBD == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(ActivityMapper.INSTANCE.toActivityDTO(activityBD));
    }

    @PutMapping
    public ResponseEntity<ActivityDTO> update(
            @Valid @RequestBody ActivityDTO activityDTO) {
        Activity activity = ActivityMapper.INSTANCE.toActivity(activityDTO);
        var activityBD = this.activityService.update(activity);
        if (activityBD == null) {
            return new ResponseEntity<>(new ActivityDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ActivityMapper.INSTANCE.toActivityDTO(activityBD), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(
            @PathVariable(value = "id") UUID id) {
        this.activityService.deleteById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @Autowired
    public void setActivityService(IActivityService activityService) {
        this.activityService = activityService;
    }

}
