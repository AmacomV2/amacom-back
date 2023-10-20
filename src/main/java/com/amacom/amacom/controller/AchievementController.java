package com.amacom.amacom.controller;

import com.amacom.amacom.dto.AchievementDTO;
import com.amacom.amacom.dto.AlarmSignDTO;
import com.amacom.amacom.dto.PersonBabysDTO;
import com.amacom.amacom.mapper.AchievementMapper;
import com.amacom.amacom.mapper.PersonBabysMapper;
import com.amacom.amacom.model.Achievement;
import com.amacom.amacom.model.PersonBabys;
import com.amacom.amacom.service.interfaces.IAchievementService;
import com.amacom.amacom.service.interfaces.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/achievement")
public class AchievementController {

    private IAchievementService achievementService;

    private ISubjectService subjectService;


    @GetMapping("/{id}")
    public ResponseEntity<AchievementDTO> findById(
            @PathVariable(value = "id") UUID id){
        Achievement achievement = this.achievementService.findById(id);
        if (achievement == null) {
            return new ResponseEntity<>(new AchievementDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(AchievementMapper.INSTANCE.toAchievementDTO(achievement), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<AchievementDTO> create(
            @Valid @RequestBody AchievementDTO achievementDTO){

        Achievement achievement  = AchievementMapper.INSTANCE.toAchievement(achievementDTO);

        achievement.setSubject(this.subjectService.getEntityFromUUID(achievementDTO.getIdSubject()));

        var achievementBD = this.achievementService.create(achievement);
        if(achievementBD == null) return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(AchievementMapper.INSTANCE.toAchievementDTO(achievementBD));
    }

    @PutMapping
    public ResponseEntity<AchievementDTO> update(
            @Valid @RequestBody AchievementDTO achievementDTO){

        Achievement achievement  = AchievementMapper.INSTANCE.toAchievement(achievementDTO);

        achievement.setSubject(this.subjectService.getEntityFromUUID(achievementDTO.getIdSubject()));

        var achievementBD = this.achievementService.update(achievement);
        if (achievementBD == null) {
            return new ResponseEntity<>(new AchievementDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(AchievementMapper.INSTANCE.toAchievementDTO(achievementBD), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(
            @PathVariable(value = "id") UUID id){
        this.achievementService.deleteById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }


    @Autowired
    public void setAchievementService(IAchievementService achievementService) {
        this.achievementService = achievementService;
    }

    @Autowired
    public void setSubjectService(ISubjectService subjectService) {
        this.subjectService = subjectService;
    }

}
