package com.amacom.amacom.service.interfaces;

import com.amacom.amacom.model.Persona;
import com.amacom.amacom.model.auth.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUsuarioService {


    List<Usuario> getAll();

    Usuario findUsuarioById(Long id);

    Usuario updateUsuario(Usuario usuario);

    void deleteUsuarioById(Long id);

    Usuario findByEmail(String email);

}
