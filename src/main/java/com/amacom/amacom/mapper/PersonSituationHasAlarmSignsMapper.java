package com.amacom.amacom.mapper;

import com.amacom.amacom.dto.PersonSituationHasAlarmSignsDTO;
import com.amacom.amacom.model.PersonSituationHasAlarmSigns;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonSituationHasAlarmSignsMapper {

    PersonSituationHasAlarmSignsMapper INSTANCE = Mappers.getMapper(PersonSituationHasAlarmSignsMapper.class);

    PersonSituationHasAlarmSigns toPersonSituationHasAlarmSigns(PersonSituationHasAlarmSignsDTO personSituationHasAlarmSignsDTO);

    @Mapping(target = "idPersonSituation", source = "personSituation.id")
    @Mapping(target = "idAlarmSign", source = "alarmSign.id")
    PersonSituationHasAlarmSignsDTO toPersonSituationHasAlarmSignsDTO(PersonSituationHasAlarmSigns personSituationHasAlarmSigns);
}
