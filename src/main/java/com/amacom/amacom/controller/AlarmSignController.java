package com.amacom.amacom.controller;

import com.amacom.amacom.dto.AlarmSignDTO;
import com.amacom.amacom.dto.GeneroDTO;
import com.amacom.amacom.mapper.AlarmSignMapper;
import com.amacom.amacom.mapper.GeneroMapper;
import com.amacom.amacom.model.AlarmSign;
import com.amacom.amacom.model.Genero;
import com.amacom.amacom.service.interfaces.IAlarmSignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/alarmSign")
public class AlarmSignController {

    private IAlarmSignService alarmSignService;


    @GetMapping("/{id}")
    public ResponseEntity<AlarmSignDTO> findById(
            @PathVariable(value = "id") UUID id){
        AlarmSign alarmSign = this.alarmSignService.findById(id);
        if (alarmSign == null) {
            return new ResponseEntity<>(new AlarmSignDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(AlarmSignMapper.INSTANCE.toAlarmSignDTO(alarmSign), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<AlarmSignDTO> create(
            @Valid @RequestBody AlarmSignDTO alarmSignDTO){
        AlarmSign alarmSign = AlarmSignMapper.INSTANCE.toAlarmSign(alarmSignDTO);
        var alarmSignBD = this.alarmSignService.create(alarmSign);
        if(alarmSignBD == null) return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(AlarmSignMapper.INSTANCE.toAlarmSignDTO(alarmSignBD));
    }

    @PutMapping
    public ResponseEntity<AlarmSignDTO> update(
            @Valid @RequestBody AlarmSignDTO alarmSignDTO){
        AlarmSign alarmSign = AlarmSignMapper.INSTANCE.toAlarmSign(alarmSignDTO);
        var alarmSignBD = this.alarmSignService.update(alarmSign);
        if (alarmSignBD == null) {
            return new ResponseEntity<>(new AlarmSignDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(AlarmSignMapper.INSTANCE.toAlarmSignDTO(alarmSignBD), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(
            @PathVariable(value = "id") UUID id){
        this.alarmSignService.deleteById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @Autowired
    public void setAlarmSignService(IAlarmSignService alarmSignService) {
        this.alarmSignService = alarmSignService;
    }

}
