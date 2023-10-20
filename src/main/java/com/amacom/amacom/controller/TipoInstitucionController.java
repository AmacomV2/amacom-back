package com.amacom.amacom.controller;

import com.amacom.amacom.dto.GeneroDTO;
import com.amacom.amacom.dto.TipoInstitucionDTO;
import com.amacom.amacom.mapper.GeneroMapper;
import com.amacom.amacom.mapper.TipoInstitucionMapper;
import com.amacom.amacom.model.Genero;
import com.amacom.amacom.model.TipoInstitucion;
import com.amacom.amacom.service.interfaces.ITipoInstitucionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/tipoInstitucion")
public class TipoInstitucionController {

    private ITipoInstitucionService tipoInstitucionService;

    @GetMapping("/{id}")
    public ResponseEntity<TipoInstitucionDTO> findById(
            @PathVariable(value = "id") UUID id){
        TipoInstitucion tipoInstitucion = this.tipoInstitucionService.findById(id);
        if (tipoInstitucion == null) {
            return new ResponseEntity<>(new TipoInstitucionDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(TipoInstitucionMapper.INSTANCE.toTipoInstitucionDTO(tipoInstitucion), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<TipoInstitucionDTO> create(
            @Valid @RequestBody TipoInstitucionDTO tipoInstitucionDTO){
        TipoInstitucion tipoInstitucion = TipoInstitucionMapper.INSTANCE.toTipoInstitucion(tipoInstitucionDTO);
        var tipoInstitucionBD = this.tipoInstitucionService.create(tipoInstitucion);
        if(tipoInstitucionBD == null) return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(TipoInstitucionMapper.INSTANCE.toTipoInstitucionDTO(tipoInstitucionBD));
    }

    @PutMapping
    public ResponseEntity<TipoInstitucionDTO> update(
            @Valid @RequestBody TipoInstitucionDTO tipoInstitucionDTO){
        TipoInstitucion tipoInstitucion = TipoInstitucionMapper.INSTANCE.toTipoInstitucion(tipoInstitucionDTO);
        var tipoInstitucionBD = this.tipoInstitucionService.update(tipoInstitucion);
        if (tipoInstitucionBD == null) {
            return new ResponseEntity<>(new TipoInstitucionDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(TipoInstitucionMapper.INSTANCE.toTipoInstitucionDTO(tipoInstitucionBD), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(
            @PathVariable(value = "id") UUID id){
        this.tipoInstitucionService.deleteById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }


    @Autowired
    public void setTipoInstitucionService(ITipoInstitucionService tipoInstitucionService) {
        this.tipoInstitucionService = tipoInstitucionService;
    }


}
