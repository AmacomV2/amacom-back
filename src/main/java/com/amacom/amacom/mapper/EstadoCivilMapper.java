package com.amacom.amacom.mapper;

import com.amacom.amacom.dto.EstadoCivilDTO;
import com.amacom.amacom.model.EstadoCivil;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EstadoCivilMapper {
    EstadoCivilMapper INSTANCE = Mappers.getMapper(EstadoCivilMapper.class);

    //DTO TO ENTITY
    EstadoCivil toEstadoCivil(EstadoCivilDTO estadoCivilDTO);

    //ENTITY TO DTO

    EstadoCivilDTO toEstadoCivilDTO(EstadoCivil estadoCivil);
}
