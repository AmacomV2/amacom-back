package com.amacom.amacom.controller;

import com.amacom.amacom.dto.UsuarioDTO;
import com.amacom.amacom.mapper.UsuarioMapper;
import com.amacom.amacom.model.auth.Usuario;
import com.amacom.amacom.service.interfaces.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private IUsuarioService usuarioService;

    @GetMapping("/getAllUsuario")
    public ResponseEntity<List<UsuarioDTO>> getAllUsuario(){
        List<Usuario> usuarioList = this.usuarioService.getAll();
        if (usuarioList == null || usuarioList.isEmpty()) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(usuarioList.stream()
                .map(UsuarioMapper.INSTANCE::toUsuarioDTO).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> findPersonaById(
            @PathVariable(value = "id") Long id){
        Usuario usuario = this.usuarioService.findUsuarioById(id);
        if (usuario == null) {
            return new ResponseEntity<>(new UsuarioDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(UsuarioMapper.INSTANCE.toUsuarioDTO(usuario), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<UsuarioDTO> updatePersona(
            @Valid @RequestBody UsuarioDTO usuarioDTO){
        Usuario usuario = UsuarioMapper.INSTANCE.toUsuario(usuarioDTO);
        var usuarioBD = this.usuarioService.updateUsuario(usuario);
        if (usuarioBD == null) {
            return new ResponseEntity<>(new UsuarioDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(UsuarioMapper.INSTANCE.toUsuarioDTO(usuarioBD), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletePersona(
            @PathVariable(value = "id") Long id){
        this.usuarioService.deleteUsuarioById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @Autowired
    public void setUsuarioService(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
}
