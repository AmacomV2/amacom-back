package com.amacom.amacom.mapper;

import com.amacom.amacom.dto.UsuarioDTO;
import com.amacom.amacom.model.auth.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.HashMap;
import java.util.Map;

@Mapper
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    Usuario toUsuario(UsuarioDTO usuarioDTO);

    @Mapping(target = "idPersona", source = "persona.id")
    @Mapping(target = "idRol", source = "rol.id")
    UsuarioDTO toUsuarioDTO(Usuario usuario);


    static Map<String, String> getClavesToSort() {
        Map<String, String> clavesToSort = new HashMap<>();
        clavesToSort.put("username", "username");
        clavesToSort.put("email", "email");
        clavesToSort.put("fechaHoraCreacion", "fechaHoraCreacion");
        return clavesToSort;
    }

}
