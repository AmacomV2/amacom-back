package com.amacom.amacom.mapper;

import com.amacom.amacom.dto.PersonSituationHasFeelingsDTO;
import com.amacom.amacom.model.PersonSituationHasFeelings;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonSituationHasFeelingsMapper {

    PersonSituationHasFeelingsMapper INSTANCE = Mappers.getMapper(PersonSituationHasFeelingsMapper.class);

    PersonSituationHasFeelings toPersonSituationHasFeelings(PersonSituationHasFeelingsDTO personSituationHasFeelingsDTO);

    @Mapping(target = "idPersonSituation", source = "personSituation.id")
    @Mapping(target = "idFeelings", source = "feelings.id")
    PersonSituationHasFeelingsDTO toPersonSituationHasFeelingsDTO(PersonSituationHasFeelings personSituationHasFeelings);
}
