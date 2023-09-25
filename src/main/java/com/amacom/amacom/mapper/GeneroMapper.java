package com.amacom.amacom.mapper;

import com.amacom.amacom.dto.GeneroDTO;
import com.amacom.amacom.model.Genero;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GeneroMapper {

    GeneroMapper INSTANCE = Mappers.getMapper(GeneroMapper.class);

    //DTO TO ENTITY

    Genero toGenero(GeneroDTO generoDTO);

    //ENTITY TO DTO

    GeneroDTO toGeneroDTO(Genero genero);
}
