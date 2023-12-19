package com.amacom.amacom.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.amacom.amacom.dto.GenderDTO;
import com.amacom.amacom.model.Gender;

@Mapper
public interface GenderMapper {

    GenderMapper INSTANCE = Mappers.getMapper(GenderMapper.class);

    // DTO TO ENTITY

    Gender toGenero(GenderDTO genderDTO);

    // ENTITY TO DTO

    GenderDTO toGeneroDTO(Gender gender);
}
