package com.amacom.amacom.service.impl;

import com.amacom.amacom.exception.DataNotFoundException;
import com.amacom.amacom.exception.ValidacionException;
import com.amacom.amacom.model.Persona;
import com.amacom.amacom.model.auth.ERole;
import com.amacom.amacom.model.auth.RegisterRequest;
import com.amacom.amacom.model.auth.Usuario;
import com.amacom.amacom.repository.IPersonaRepository;
import com.amacom.amacom.repository.auth.IUsuarioRepository;
import com.amacom.amacom.service.interfaces.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    private IUsuarioRepository usuarioRepository;



    @Override
    public List<Usuario> getAll() {
        return this.usuarioRepository.findAll();
    }

    @Override
    public Usuario findUsuarioById(Long id) {
        return this.usuarioRepository.findById(id).orElseThrow(DataNotFoundException::new);
    }

    @Override
    public Usuario updateUsuario(Usuario usuario) {
        this.validarRegistro(usuario);
        var usuarioBD = this.usuarioRepository.findById(usuario.getId()).orElseThrow(DataNotFoundException::new);
        usuarioBD.setIdRol(usuario.getIdRol());
        if(usuario.getIdRol() != null){
            if(usuario.getIdRol() == 1){
                usuarioBD.setEnumRol(ERole.ROLE_ADMIN);
            }
            if(usuario.getIdRol() == 2){
                usuarioBD.setEnumRol(ERole.ROLE_SUPER_ADMIN);
            }
            if(usuario.getIdRol() == 3){
                usuarioBD.setEnumRol(ERole.ROLE_ENFERMERIA);
            }
            if(usuario.getIdRol() == 4){
                usuarioBD.setEnumRol(ERole.ROLE_USER);
            }
        }
        else {
            usuarioBD.setEnumRol(ERole.ROLE_USER);
        }
        usuarioBD.setUsername(usuario.getUsername());
        usuarioBD.setEmail(usuario.getEmail());
        usuarioBD.setFechaHoraModificacion(new Date());
        return this.usuarioRepository.save(usuarioBD);
    }

    @Override
    public void deleteUsuarioById(Long id) {
        var usuarioBD = this.usuarioRepository.findById(id).orElseThrow(DataNotFoundException::new);
        this.usuarioRepository.deleteById(usuarioBD.getId());
    }


    private void validarRegistro(Usuario usuario){

        var existsSimilar = this.usuarioRepository.existsByUsernameOrEmail(usuario.getId(), usuario.getUsername(), usuario.getEmail());
        if (Boolean.TRUE.equals(existsSimilar))
            throw new ValidacionException("Ya existe un usuario con este username o este email.");

        var existsSimilarByIdPersona = this.usuarioRepository.existsByIdPersona(usuario.getId(), usuario.getPersona().getId());
        if (Boolean.TRUE.equals(existsSimilarByIdPersona))
            throw new ValidacionException("Ya existe un registro diferente para esta persona.");
    }

    @Override
    public Usuario findByEmail(String email) {
        var usuario = this.usuarioRepository.findByEmail(email);
        if(usuario != null){
            return usuario;
        }else{
            throw new ValidacionException("El email proporcionado no se encuentra registrado en la base de datos.");
        }
    }

    @Autowired
    public void setUsuarioRepository(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
}
