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

import com.amacom.amacom.dto.PhaseDTO;
import com.amacom.amacom.mapper.PhaseMapper;
import com.amacom.amacom.model.Phase;
import com.amacom.amacom.service.interfaces.IPhaseService;

@RestController
@RequestMapping("/phrase")
public class PhaseController {

    private IPhaseService phraseService;

    @GetMapping("/{id}")
    public ResponseEntity<PhaseDTO> findById(
            @PathVariable(value = "id") UUID id) {
        Phase phrase = this.phraseService.findById(id);
        if (phrase == null) {
            return new ResponseEntity<>(new PhaseDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(PhaseMapper.INSTANCE.toPhraseDTO(phrase), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<PhaseDTO> create(
            @Valid @RequestBody PhaseDTO phraseDTO) {
        Phase phrase = PhaseMapper.INSTANCE.toPhrase(phraseDTO);
        var phraseBD = this.phraseService.create(phrase);
        if (phraseBD == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(PhaseMapper.INSTANCE.toPhraseDTO(phraseBD));
    }

    @PutMapping
    public ResponseEntity<PhaseDTO> update(
            @Valid @RequestBody PhaseDTO phraseDTO) {
        Phase phrase = PhaseMapper.INSTANCE.toPhrase(phraseDTO);
        var phraseBD = this.phraseService.update(phrase);
        if (phraseBD == null) {
            return new ResponseEntity<>(new PhaseDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(PhaseMapper.INSTANCE.toPhraseDTO(phraseBD), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(
            @PathVariable(value = "id") UUID id) {
        this.phraseService.deleteById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @Autowired
    public void setPhraseService(IPhaseService phraseService) {
        this.phraseService = phraseService;
    }

}
