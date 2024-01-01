package com.amacom.amacom.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.amacom.amacom.dto.FeelingsDTO;
import com.amacom.amacom.dto.response.ErrorDTO;
import com.amacom.amacom.dto.response.ResponseDTO;
import com.amacom.amacom.dto.response.SuccessDTO;
import com.amacom.amacom.mapper.FeelingsMapper;
import com.amacom.amacom.model.Feelings;
import com.amacom.amacom.service.interfaces.IFeelingsService;
import com.amacom.amacom.util.ITools;

@RestController
@RequestMapping("/feelings")
public class FeelingsController {

    private IFeelingsService feelingsService;

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> findById(
            @PathVariable(value = "id") UUID id) {
        Feelings feelings = this.feelingsService.findById(id);
        if (feelings == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new SuccessDTO(FeelingsMapper.INSTANCE.toFeelingsDTO(feelings)), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<ResponseDTO> findPageable(
            Pageable pageable,
            @RequestParam(name = "query", required = false) String query) {

        try {
            Page<Feelings> page = this.feelingsService.findFeeling(query,
                    ITools.getPageRequest(pageable, FeelingsMapper.getSortKeys()));
            return new ResponseEntity<>(new SuccessDTO(page
                    .map(FeelingsMapper.INSTANCE::toFeelingsDTO)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDTO(e), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> create(
            @Valid @RequestBody FeelingsDTO feelingsDTO) {
        Feelings feelings = FeelingsMapper.INSTANCE.toFeelings(feelingsDTO);
        var feelingsBD = this.feelingsService.create(feelings);
        if (feelingsBD == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(new SuccessDTO(FeelingsMapper.INSTANCE.toFeelingsDTO(feelingsBD)));
    }

    @PutMapping
    public ResponseEntity<ResponseDTO> update(
            @Valid @RequestBody FeelingsDTO feelingsDTO) {
        Feelings feelings = FeelingsMapper.INSTANCE.toFeelings(feelingsDTO);
        var feelingsBD = this.feelingsService.update(feelings);
        if (feelingsBD == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new SuccessDTO(FeelingsMapper.INSTANCE.toFeelingsDTO(feelingsBD)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> delete(
            @PathVariable(value = "id") UUID id) {
        this.feelingsService.deleteById(id);
        return ResponseEntity.ok(new SuccessDTO());
    }

    @Autowired
    public void setFeelingsService(IFeelingsService feelingsService) {
        this.feelingsService = feelingsService;
    }

}
