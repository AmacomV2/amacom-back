package com.amacom.amacom.mapper;

import com.amacom.amacom.dto.PhraseDTO;
import com.amacom.amacom.model.Phrase;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PhraseMapper {

    PhraseMapper INSTANCE = Mappers.getMapper(PhraseMapper.class);

    Phrase toPhrase(PhraseDTO phraseDTO);

    PhraseDTO toPhraseDTO(Phrase phrase);
}
