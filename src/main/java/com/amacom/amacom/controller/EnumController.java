package com.amacom.amacom.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amacom.amacom.model.EAffectationDegree;
import com.amacom.amacom.model.EConsultationAlert;
import com.amacom.amacom.model.EConsultationStatus;
import com.amacom.amacom.model.EEventStatus;
import com.amacom.amacom.model.EScore;

@RestController
@RequestMapping("/enum")
public class EnumController {
    //
    @GetMapping("/consultationAlerts")
    public ResponseEntity<List<EConsultationAlert>> getEConsultationAlert() {
        List<EConsultationAlert> status = Arrays.asList(EConsultationAlert.values());
        return ResponseEntity.ok(status);
    }

    @GetMapping("/consultationStatuses")
    public ResponseEntity<List<EConsultationStatus>> getEConsultationStatus() {
        List<EConsultationStatus> status = Arrays.asList(EConsultationStatus.values());
        return ResponseEntity.ok(status);
    }

    @GetMapping("/eventStatus")
    public ResponseEntity<List<EEventStatus>> getEEventStatus() {
        List<EEventStatus> status = Arrays.asList(EEventStatus.values());
        return ResponseEntity.ok(status);
    }

    @GetMapping("/affectationDegree")
    public ResponseEntity<List<EAffectationDegree>> getEAffectationDegree() {
        List<EAffectationDegree> status = Arrays.asList(EAffectationDegree.values());
        return ResponseEntity.ok(status);
    }

    @GetMapping("/score")
    public ResponseEntity<List<EScore>> getEScore() {
        List<EScore> status = Arrays.asList(EScore.values());
        return ResponseEntity.ok(status);
    }
}
