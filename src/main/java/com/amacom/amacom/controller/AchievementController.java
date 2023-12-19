package com.amacom.amacom.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.amacom.amacom.dto.AchievementDTO;
import com.amacom.amacom.mapper.AchievementMapper;
import com.amacom.amacom.model.Achievement;
import com.amacom.amacom.service.interfaces.IAchievementService;
import com.amacom.amacom.service.interfaces.ISubjectService;
import com.amacom.amacom.util.ITools;

@RestController
@RequestMapping("/achievement")
public class AchievementController {

    private IAchievementService achievementService;

    private ISubjectService subjectService;

    @GetMapping("/consulta")
    public ResponseEntity<Page<AchievementDTO>> findPageable(
            Pageable pageable,
            @RequestParam(name = "subjectId", required = false) UUID subjectId,
            @RequestParam(name = "query", required = false) String query) {

        var achievementPage = this.achievementService.findAchievement(subjectId, query,
                ITools.getPageRequest(pageable, AchievementMapper.getClavesToSort()));

        if (achievementPage == null || achievementPage.getContent().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(achievementPage
                .map(AchievementMapper.INSTANCE::toAchievementDTO), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AchievementDTO> findById(
            @PathVariable(value = "id") UUID id) {
        Achievement achievement = this.achievementService.findById(id);
        if (achievement == null) {
            return new ResponseEntity<>(new AchievementDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(AchievementMapper.INSTANCE.toAchievementDTO(achievement), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<AchievementDTO> create(
            @Valid @RequestBody AchievementDTO achievementDTO) {

        Achievement achievement = AchievementMapper.INSTANCE.toAchievement(achievementDTO);

        achievement.setSubject(this.subjectService.getEntityFromUUID(achievementDTO.getSubjectId()));

        var achievementBD = this.achievementService.create(achievement);
        if (achievementBD == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(AchievementMapper.INSTANCE.toAchievementDTO(achievementBD));
    }

    @PutMapping
    public ResponseEntity<AchievementDTO> update(
            @Valid @RequestBody AchievementDTO achievementDTO) {

        Achievement achievement = AchievementMapper.INSTANCE.toAchievement(achievementDTO);

        achievement.setSubject(this.subjectService.getEntityFromUUID(achievementDTO.getSubjectId()));

        var achievementBD = this.achievementService.update(achievement);
        if (achievementBD == null) {
            return new ResponseEntity<>(new AchievementDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(AchievementMapper.INSTANCE.toAchievementDTO(achievementBD), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(
            @PathVariable(value = "id") UUID id) {
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
