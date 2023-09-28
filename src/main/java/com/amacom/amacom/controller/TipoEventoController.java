package com.amacom.amacom.controller;

import com.amacom.amacom.dto.GeneroDTO;
import com.amacom.amacom.dto.TipoEventoDTO;
import com.amacom.amacom.mapper.GeneroMapper;
import com.amacom.amacom.mapper.TipoEventoMapper;
import com.amacom.amacom.model.Genero;
import com.amacom.amacom.model.TipoEvento;
import com.amacom.amacom.repository.ITipoEventoRepository;
import com.amacom.amacom.service.interfaces.ITipoEventoService;
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
@RequestMapping("/tipoEvento")
public class TipoEventoController {

    private ITipoEventoService tipoEventoService;

    @GetMapping("/getAll")
    public ResponseEntity<List<TipoEventoDTO>> getAll(){
        List<TipoEvento> tipoEventoList = this.tipoEventoService.getAll();
        if (tipoEventoList == null || tipoEventoList.isEmpty()) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tipoEventoList.stream()
                .map(TipoEventoMapper.INSTANCE::toTipoEventoDTO).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoEventoDTO> findById(
            @PathVariable(value = "id") UUID id){
        TipoEvento tipoEvento = this.tipoEventoService.findById(id);
        if (tipoEvento == null) {
            return new ResponseEntity<>(new TipoEventoDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(TipoEventoMapper.INSTANCE.toTipoEventoDTO(tipoEvento), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<TipoEventoDTO> create(
            @Valid @RequestBody TipoEventoDTO tipoEventoDTO){
        TipoEvento tipoEvento = TipoEventoMapper.INSTANCE.toTipoEvento(tipoEventoDTO);
        var tipoEventoBD = this.tipoEventoService.create(tipoEvento);
        if(tipoEventoBD == null) return new ResponseEntity<>(HttpStatus.CONFLICT);
        return ResponseEntity.ok(TipoEventoMapper.INSTANCE.toTipoEventoDTO(tipoEventoBD));
    }

    @PutMapping
    public ResponseEntity<TipoEventoDTO> update(
            @Valid @RequestBody TipoEventoDTO tipoEventoDTO){
        TipoEvento tipoEvento = TipoEventoMapper.INSTANCE.toTipoEvento(tipoEventoDTO);
        var tipoEventoBD = this.tipoEventoService.update(tipoEvento);
        if (tipoEventoBD == null) {
            return new ResponseEntity<>(new TipoEventoDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(TipoEventoMapper.INSTANCE.toTipoEventoDTO(tipoEventoBD), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(
            @PathVariable(value = "id") UUID id){
        this.tipoEventoService.deleteById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @Autowired
    public void setTipoEventoService(ITipoEventoService tipoEventoService) {
        this.tipoEventoService = tipoEventoService;
    }
}
