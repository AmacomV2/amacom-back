package com.amacom.amacom.service.interfaces;

import com.amacom.amacom.model.EstadoCivil;
import com.amacom.amacom.model.Persona;
import com.amacom.amacom.model.auth.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface IUsuarioService {

    Usuario getEntityFromUUID(UUID uuid);

    Page<Usuario> findUsuario(UUID idPersona, String query, Pageable pageable);

    List<Usuario> getAll();

    Usuario findUsuarioById(UUID id);

    Usuario updateUsuario(Usuario usuario);

    void deleteUsuarioById(UUID id);

    Usuario findByEmail(String email);

}
