package com.amacom.amacom.controller;

import com.amacom.amacom.dto.GeneroDTO;
import com.amacom.amacom.dto.TipoDocumentoDTO;
import com.amacom.amacom.mapper.GeneroMapper;
import com.amacom.amacom.mapper.TipoDocumentoMapper;
import com.amacom.amacom.model.Genero;
import com.amacom.amacom.model.TipoDocumento;
import com.amacom.amacom.service.interfaces.ITipoDocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tipoDocumento")
public class TipoDocumentoController {

    private ITipoDocumentoService tipoDocumentoService;

    @GetMapping("/getAll")
    public ResponseEntity<List<TipoDocumentoDTO>> getAll(){
        List<TipoDocumento> tipoDocumentoList= this.tipoDocumentoService.getAll();
        if (tipoDocumentoList == null || tipoDocumentoList.isEmpty()) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tipoDocumentoList.stream()
                .map(TipoDocumentoMapper.INSTANCE::toTipoDocumentoDTO).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoDocumentoDTO> findById(
            @PathVariable(value = "id") UUID id){
        TipoDocumento tipoDocumento = this.tipoDocumentoService.findById(id);
        if (tipoDocumento == null) {
            return new ResponseEntity<>(new TipoDocumentoDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(TipoDocumentoMapper.INSTANCE.toTipoDocumentoDTO(tipoDocumento), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<TipoDocumentoDTO> create(
            @Valid @RequestBody TipoDocumentoDTO tipoDocumentoDTO){
        TipoDocumento tipoDocumento = TipoDocumentoMapper.INSTANCE.toTipoDocumento(tipoDocumentoDTO);
        var tipoDocumentoBD = this.tipoDocumentoService.create(tipoDocumento);
        if(tipoDocumentoBD == null) return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(TipoDocumentoMapper.INSTANCE.toTipoDocumentoDTO(tipoDocumentoBD));
    }

    @PutMapping
    public ResponseEntity<TipoDocumentoDTO> update(
            @Valid @RequestBody TipoDocumentoDTO tipoDocumentoDTO){
        TipoDocumento tipoDocumento = TipoDocumentoMapper.INSTANCE.toTipoDocumento(tipoDocumentoDTO);
        var tipoDocumentoBD = this.tipoDocumentoService.update(tipoDocumento);
        if (tipoDocumentoBD == null) {
            return new ResponseEntity<>(new TipoDocumentoDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(TipoDocumentoMapper.INSTANCE.toTipoDocumentoDTO(tipoDocumentoBD), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(
            @PathVariable(value = "id") UUID id){
        this.tipoDocumentoService.deleteById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }


    @Autowired
    public void setTipoDocumentoService(ITipoDocumentoService tipoDocumentoService) {
        this.tipoDocumentoService = tipoDocumentoService;
    }
}
