package com.amacom.amacom.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amacom.amacom.dto.AlarmSignDTO;
import com.amacom.amacom.dto.response.ErrorDTO;
import com.amacom.amacom.dto.response.ResponseDTO;
import com.amacom.amacom.dto.response.SuccessDTO;
import com.amacom.amacom.mapper.AlarmSignMapper;
import com.amacom.amacom.model.AlarmSign;
import com.amacom.amacom.model.EAlarmSignType;
import com.amacom.amacom.service.interfaces.IAlarmSignService;
import com.amacom.amacom.util.ITools;

@RestController
@RequestMapping("/alarmSign")
public class AlarmSignController {

    private IAlarmSignService alarmSignService;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> findById(
            @PathVariable(value = "id") UUID id) {
        AlarmSign alarmSign = this.alarmSignService.findById(id);
        if (alarmSign == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new SuccessDTO(AlarmSignMapper.INSTANCE.toAlarmSignDTO(alarmSign)), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<ResponseDTO> findPageable(
            Pageable pageable,
            @RequestParam(name = "query", required = false) String query,
            @RequestParam(name = "type", required = false) @Nullable EAlarmSignType type) {

        try {
            Page<AlarmSign> page = this.alarmSignService.findAlarmSign(type, query,
                    ITools.getPageRequest(pageable, AlarmSignMapper.getSortKeys()));
            return new ResponseEntity<>(new SuccessDTO(page
                    .map(AlarmSignMapper.INSTANCE::toAlarmSignDTO)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDTO(e), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> create(
            @Valid @RequestBody AlarmSignDTO alarmSignDTO) {
        AlarmSign alarmSign = AlarmSignMapper.INSTANCE.toAlarmSign(alarmSignDTO);
        var alarmSignBD = this.alarmSignService.create(alarmSign);
        if (alarmSignBD == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(new SuccessDTO(AlarmSignMapper.INSTANCE.toAlarmSignDTO(alarmSignBD)));
    }

    @PutMapping
    public ResponseEntity<ResponseDTO> update(
            @Valid @RequestBody AlarmSignDTO alarmSignDTO) {
        AlarmSign alarmSign = AlarmSignMapper.INSTANCE.toAlarmSign(alarmSignDTO);
        var alarmSignBD = this.alarmSignService.update(alarmSign);
        if (alarmSignBD == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new SuccessDTO(AlarmSignMapper.INSTANCE.toAlarmSignDTO(alarmSignBD)),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> delete(
            @PathVariable(value = "id") UUID id) {
        this.alarmSignService.deleteById(id);
        return ResponseEntity.ok(new SuccessDTO());
    }

    @Autowired
    public void setAlarmSignService(IAlarmSignService alarmSignService) {
        this.alarmSignService = alarmSignService;
    }

}
