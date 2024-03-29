package com.amacom.amacom.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amacom.amacom.dto.response.ResponseDTO;
import com.amacom.amacom.dto.response.SuccessDTO;
import com.amacom.amacom.mapper.CivilStatusMapper;
import com.amacom.amacom.model.CivilStatus;
import com.amacom.amacom.service.interfaces.ICivilStatusService;

@RestController
@RequestMapping("/civilStatus")
public class CivilStatusController {

    private ICivilStatusService civilStatusService;

    @GetMapping("/getAll")
    public ResponseEntity<ResponseDTO> getAll() {
        List<CivilStatus> civilStatusList = this.civilStatusService.getAll();

        return new ResponseEntity<>(new SuccessDTO(civilStatusList.stream()
                .map(CivilStatusMapper.INSTANCE::toCivilStatusDTO).collect(Collectors.toList())), HttpStatus.OK);
    }

    @Autowired
    public void setCivilStatusService(ICivilStatusService civilStatusService) {
        this.civilStatusService = civilStatusService;
    }
}
