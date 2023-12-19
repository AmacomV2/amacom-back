package com.amacom.amacom.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.amacom.amacom.dto.SituationTypeDTO;
import com.amacom.amacom.model.SituationType;

@Mapper
public interface SituationTypeMapper {

    SituationTypeMapper INSTANCE = Mappers.getMapper(SituationTypeMapper.class);

    SituationType situationType(SituationTypeDTO situationTypeDTO);

    SituationTypeDTO situationTypeDTO(SituationType situationType);
}
