package com.amacom.amacom.controller;

import com.amacom.amacom.dto.PersonaDTO;
import com.amacom.amacom.dto.UsuarioDTO;
import com.amacom.amacom.mapper.PersonaMapper;
import com.amacom.amacom.mapper.UsuarioMapper;
import com.amacom.amacom.model.auth.Usuario;
import com.amacom.amacom.service.interfaces.IPersonaService;
import com.amacom.amacom.service.interfaces.IUsuarioService;
import com.amacom.amacom.util.ITools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private IUsuarioService usuarioService;

    private IPersonaService personaService;

    @GetMapping("/consulta")
    public ResponseEntity<Page<UsuarioDTO>> findPageable(
            Pageable pageable,
            @RequestParam(name = "idPersona", required = false) UUID idPersona,
            @RequestParam(name = "query", required = false) String query) {

        var usuarioPage = this.usuarioService.findUsuario(idPersona, query, ITools.getPageRequest(pageable, UsuarioMapper.getClavesToSort()));

        if (usuarioPage == null || usuarioPage.getContent().isEmpty()) {
            return new ResponseEntity<>( HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(usuarioPage
                .map(UsuarioMapper.INSTANCE::toUsuarioDTO), HttpStatus.OK);
    }


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
    public ResponseEntity<UsuarioDTO> findUsuarioById(
            @PathVariable(value = "id") UUID id){
        Usuario usuario = this.usuarioService.findUsuarioById(id);
        if (usuario == null) {
            return new ResponseEntity<>(new UsuarioDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(UsuarioMapper.INSTANCE.toUsuarioDTO(usuario), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<UsuarioDTO> updateUsuario(
            @Valid @RequestBody UsuarioDTO usuarioDTO){

        Usuario usuario = UsuarioMapper.INSTANCE.toUsuario(usuarioDTO);
        usuario.setPersona(this.personaService.getPersonaFromUUID(usuarioDTO.getIdPersona()));
        var usuarioBD = this.usuarioService.updateUsuario(usuario);
        if (usuarioBD == null) {
            return new ResponseEntity<>(new UsuarioDTO(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(UsuarioMapper.INSTANCE.toUsuarioDTO(usuarioBD), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteUsuario(
            @PathVariable(value = "id") UUID id){
        this.usuarioService.deleteUsuarioById(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @Autowired
    public void setUsuarioService(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Autowired
    public void setPersonaService(IPersonaService personaService) {
        this.personaService = personaService;
    }
}
