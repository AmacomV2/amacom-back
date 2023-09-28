package com.amacom.amacom.mapper;

import com.amacom.amacom.dto.UsuarioDTO;
import com.amacom.amacom.model.auth.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    Usuario toUsuario(UsuarioDTO usuarioDTO);

    @Mapping(target = "idPersona", source = "persona.id")
    @Mapping(target = "idRol", source = "rol.id")
    UsuarioDTO toUsuarioDTO(Usuario usuario);


}
