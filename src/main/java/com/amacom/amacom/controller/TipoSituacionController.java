package com.amacom.amacom.controller;

import com.amacom.amacom.dto.GeneroDTO;
import com.amacom.amacom.dto.TipoSituacionDTO;
import com.amacom.amacom.mapper.GeneroMapper;
import com.amacom.amacom.mapper.TipoSituacionMapper;
import com.amacom.amacom.model.Genero;
import com.amacom.amacom.model.TipoSituacion;
import com.amacom.amacom.service.interfaces.ITipoSituacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/tipoSituacion")
public class TipoSituacionController {

    private ITipoSituacionService tipoSituacionService;


    @GetMapping("/{id}")
    public ResponseEntity<TipoSituacionDTO> findById(
            @PathVariable(value = "id") UUID id){
        TipoSituacion tipoSituacion = this.tipoSituacionService.findById(id);
        if (tipoSituacion == null) {
            return new ResponseEntity<>(new TipoSituacionDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(TipoSituacionMapper.INSTANCE.toTipoSituacionDTO(tipoSituacion), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<TipoSituacionDTO> create(
            @Valid @RequestBody TipoSituacionDTO tipoSituacionDTO){
        TipoSituacion tipoSituacion = TipoSituacionMapper.INSTANCE.toTipoSituacion(tipoSituacionDTO);
        var tipoSituacionBD = this.tipoSituacionService.create(tipoSituacion);
        if(tipoSituacionBD == null) return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(TipoSituacionMapper.INSTANCE.toTipoSituacionDTO(tipoSituacionBD));
    }

    @PutMapping
    public ResponseEntity<TipoSituacionDTO> update(
            @Valid @RequestBody TipoSituacionDTO tipoSituacionDTO){
        TipoSituacion tipoSituacion = TipoSituacionMapper.INSTANCE.toTipoSituacion(tipoSituacionDTO);
        var tipoSituacionBD = this.tipoSituacionService.update(tipoSituacion);
        if (tipoSituacionBD == null) {
            return new ResponseEntity<>(new TipoSituacionDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(TipoSituacionMapper.INSTANCE.toTipoSituacionDTO(tipoSituacionBD), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(
            @PathVariable(value = "id") UUID id){
        this.tipoSituacionService.deleteById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }


    @Autowired
    public void setTipoSituacionService(ITipoSituacionService tipoSituacionService) {
        this.tipoSituacionService = tipoSituacionService;
    }


}
