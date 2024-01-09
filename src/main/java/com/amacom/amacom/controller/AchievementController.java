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

import com.amacom.amacom.dto.AchievementDTO;
import com.amacom.amacom.dto.response.ErrorDTO;
import com.amacom.amacom.dto.response.ResponseDTO;
import com.amacom.amacom.dto.response.SuccessDTO;
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

    @GetMapping("/search")
    public ResponseEntity<ResponseDTO> findPageable(
            Pageable pageable,
            @RequestParam(name = "subjectId", required = false) UUID subjectId,
            @RequestParam(name = "query", required = false) String query) {

        try {
            var achievementPage = this.achievementService.findAchievement(subjectId, query,
                    ITools.getPageRequest(pageable, AchievementMapper.getSortKeys()));

            return new ResponseEntity<>(new SuccessDTO(achievementPage
                    .map(AchievementMapper.INSTANCE::toAchievementDTO)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDTO(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> findById(
            @PathVariable(value = "id") UUID id) {
        try {
            Achievement achievement = this.achievementService.findById(id);

            return new ResponseEntity<>(new SuccessDTO(AchievementMapper.INSTANCE.toAchievementDTO(achievement)),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDTO(e.getLocalizedMessage()), HttpStatus.OK);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> create(
            @Valid @RequestBody AchievementDTO achievementDTO) {

        try {
            Achievement achievement = AchievementMapper.INSTANCE.toAchievement(achievementDTO);

            achievement.setSubject(this.subjectService.getEntityFromUUID(achievementDTO.getSubjectId()));

            var achievementBD = this.achievementService.create(achievement);
            if (achievementBD == null)
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            return ResponseEntity.ok(new SuccessDTO(AchievementMapper.INSTANCE.toAchievementDTO(achievementBD)));
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDTO(e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<ResponseDTO> update(
            @Valid @RequestBody AchievementDTO achievementDTO) {

        try {
            Achievement achievement = AchievementMapper.INSTANCE.toAchievement(achievementDTO);

            achievement.setSubject(this.subjectService.getEntityFromUUID(achievementDTO.getSubjectId()));

            var achievementBD = this.achievementService.update(achievement);

            return ResponseEntity.ok(new SuccessDTO(AchievementMapper.INSTANCE.toAchievementDTO(achievementBD)));
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDTO(e.getLocalizedMessage()), HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> delete(
            @PathVariable(value = "id") UUID id) {
        try {
            this.achievementService.deleteById(id);
            return ResponseEntity.ok(new SuccessDTO());
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDTO(e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
