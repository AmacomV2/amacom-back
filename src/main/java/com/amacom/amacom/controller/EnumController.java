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
    // TODO: Translate all this shit
    @GetMapping("/alertaConsulta")
    public ResponseEntity<List<EConsultationAlert>> getEConsultationAlert() {
        List<EConsultationAlert> statuss = Arrays.asList(EConsultationAlert.values());
        return ResponseEntity.ok(statuss);
    }

    @GetMapping("/statusConsulta")
    public ResponseEntity<List<EConsultationStatus>> getEConsultationStatus() {
        List<EConsultationStatus> statuss = Arrays.asList(EConsultationStatus.values());
        return ResponseEntity.ok(statuss);
    }

    @GetMapping("/eventStatus")
    public ResponseEntity<List<EEventStatus>> getEEventStatus() {
        List<EEventStatus> statuss = Arrays.asList(EEventStatus.values());
        return ResponseEntity.ok(statuss);
    }

    @GetMapping("/affectationDegree")
    public ResponseEntity<List<EAffectationDegree>> getEAffectationDegree() {
        List<EAffectationDegree> statuss = Arrays.asList(EAffectationDegree.values());
        return ResponseEntity.ok(statuss);
    }

    @GetMapping("/score")
    public ResponseEntity<List<EScore>> getEScore() {
        List<EScore> statuss = Arrays.asList(EScore.values());
        return ResponseEntity.ok(statuss);
    }
}
