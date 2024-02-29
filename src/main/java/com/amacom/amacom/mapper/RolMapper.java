package com.amacom.amacom.mapper;

import com.amacom.amacom.dto.RolDTO;
import com.amacom.amacom.model.auth.Rol;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RolMapper {

    RolMapper INSTANCE = Mappers.getMapper(RolMapper.class);

    Rol toRol(RolDTO rolDTO);

    RolDTO toRolDTO(Rol rol);
}
