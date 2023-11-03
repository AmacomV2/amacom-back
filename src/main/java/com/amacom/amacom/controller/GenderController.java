package com.amacom.amacom.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

import com.amacom.amacom.dto.GenderDTO;
import com.amacom.amacom.mapper.GenderMapper;
import com.amacom.amacom.model.Gender;
import com.amacom.amacom.service.interfaces.IGenderService;

@RestController
@RequestMapping("/gender")
public class GenderController {

    private IGenderService genderService;

    @GetMapping("/getAll")
    public ResponseEntity<List<GenderDTO>> getAll() {
        List<Gender> genderList = this.genderService.getAll();
        if (genderList == null || genderList.isEmpty()) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(genderList.stream()
                .map(GenderMapper.INSTANCE::toGeneroDTO).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenderDTO> findById(
            @PathVariable(value = "id") UUID id) {
        Gender gender = this.genderService.findById(id);
        if (gender == null) {
            return new ResponseEntity<>(new GenderDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(GenderMapper.INSTANCE.toGeneroDTO(gender), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<GenderDTO> create(
            @Valid @RequestBody GenderDTO genderDTO) {
        Gender gender = GenderMapper.INSTANCE.toGenero(genderDTO);
        var genderBD = this.genderService.create(gender);
        if (genderBD == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(GenderMapper.INSTANCE.toGeneroDTO(genderBD));
    }

    @PutMapping
    public ResponseEntity<GenderDTO> update(
            @Valid @RequestBody GenderDTO genderDTO) {
        Gender gender = GenderMapper.INSTANCE.toGenero(genderDTO);
        var genderBD = this.genderService.update(gender);
        if (genderBD == null) {
            return new ResponseEntity<>(new GenderDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(GenderMapper.INSTANCE.toGeneroDTO(genderBD), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(
            @PathVariable(value = "id") UUID id) {
        this.genderService.deleteById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @Autowired
    public void setGenderService(IGenderService genderService) {
        this.genderService = genderService;
    }
}
