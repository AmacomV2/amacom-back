package com.amacom.amacom.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.amacom.amacom.dto.CivilStatusDTO;
import com.amacom.amacom.model.CivilStatus;

@Mapper
public interface CivilStatusMapper {
    CivilStatusMapper INSTANCE = Mappers.getMapper(CivilStatusMapper.class);

    // DTO TO ENTITY
    CivilStatus toStatusCivil(CivilStatusDTO civilStatusDTO);

    // ENTITY TO DTO

    CivilStatusDTO toCivilStatusDTO(CivilStatus statusCivil);
}
