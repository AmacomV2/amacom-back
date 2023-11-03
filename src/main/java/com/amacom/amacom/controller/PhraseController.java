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

import com.amacom.amacom.dto.PhraseDTO;
import com.amacom.amacom.mapper.PhraseMapper;
import com.amacom.amacom.model.Phrase;
import com.amacom.amacom.service.interfaces.IPhraseService;

@RestController
@RequestMapping("/phrase")
public class PhraseController {

    private IPhraseService phraseService;

    @GetMapping("/{id}")
    public ResponseEntity<PhraseDTO> findById(
            @PathVariable(value = "id") UUID id) {
        Phrase phrase = this.phraseService.findById(id);
        if (phrase == null) {
            return new ResponseEntity<>(new PhraseDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(PhraseMapper.INSTANCE.toPhraseDTO(phrase), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<PhraseDTO> create(
            @Valid @RequestBody PhraseDTO phraseDTO) {
        Phrase phrase = PhraseMapper.INSTANCE.toPhrase(phraseDTO);
        var phraseBD = this.phraseService.create(phrase);
        if (phraseBD == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(PhraseMapper.INSTANCE.toPhraseDTO(phraseBD));
    }

    @PutMapping
    public ResponseEntity<PhraseDTO> update(
            @Valid @RequestBody PhraseDTO phraseDTO) {
        Phrase phrase = PhraseMapper.INSTANCE.toPhrase(phraseDTO);
        var phraseBD = this.phraseService.update(phrase);
        if (phraseBD == null) {
            return new ResponseEntity<>(new PhraseDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(PhraseMapper.INSTANCE.toPhraseDTO(phraseBD), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(
            @PathVariable(value = "id") UUID id) {
        this.phraseService.deleteById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @Autowired
    public void setPhraseService(IPhraseService phraseService) {
        this.phraseService = phraseService;
    }

}
