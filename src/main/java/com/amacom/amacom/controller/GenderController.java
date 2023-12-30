package com.amacom.amacom.controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.amacom.amacom.dto.GenderDTO;
import com.amacom.amacom.dto.response.ResponseDTO;
import com.amacom.amacom.dto.response.SuccessDTO;
import com.amacom.amacom.mapper.GenderMapper;
import com.amacom.amacom.model.Gender;
import com.amacom.amacom.service.interfaces.IGenderService;

@RestController
@RequestMapping("/genders")
public class GenderController {

    private IGenderService genderService;

    @GetMapping("/getAll")
    public ResponseEntity<ResponseDTO> getAll() {
        List<Gender> genderList = this.genderService.getAll();

        return new ResponseEntity<>(new SuccessDTO(genderList.stream()
                .map(GenderMapper.INSTANCE::toGeneroDTO).collect(Collectors.toList())), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> findById(
            @PathVariable(value = "id") UUID id) {
        Gender gender = this.genderService.findById(id);
        return new ResponseEntity<>(new SuccessDTO(GenderMapper.INSTANCE.toGeneroDTO(gender)), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> create(
            @Valid @RequestBody GenderDTO genderDTO) {
        Gender gender = GenderMapper.INSTANCE.toGenero(genderDTO);
        var genderBD = this.genderService.create(gender);
        if (genderBD == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(new SuccessDTO(GenderMapper.INSTANCE.toGeneroDTO(genderBD)));
    }

    @PutMapping
    public ResponseEntity<ResponseDTO> update(
            @Valid @RequestBody GenderDTO genderDTO) {
        Gender gender = GenderMapper.INSTANCE.toGenero(genderDTO);
        var genderBD = this.genderService.update(gender);
        if (genderBD == null) {
            return new ResponseEntity<>(new SuccessDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new SuccessDTO(GenderMapper.INSTANCE.toGeneroDTO(genderBD)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> delete(
            @PathVariable(value = "id") UUID id) {
        this.genderService.deleteById(id);
        return ResponseEntity.ok(new SuccessDTO());
    }

    @Autowired
    public void setGenderService(IGenderService genderService) {
        this.genderService = genderService;
    }
}
