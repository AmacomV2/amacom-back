package com.amacom.amacom.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.amacom.amacom.dto.PhaseDTO;
import com.amacom.amacom.model.Phase;

@Mapper
public interface PhaseMapper {

    PhaseMapper INSTANCE = Mappers.getMapper(PhaseMapper.class);

    Phase toPhrase(PhaseDTO phraseDTO);

    PhaseDTO toPhraseDTO(Phase phrase);
}
