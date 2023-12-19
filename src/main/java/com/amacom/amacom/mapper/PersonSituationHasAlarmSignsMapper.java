package com.amacom.amacom.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.amacom.amacom.dto.PersonSituationHasAlarmSignsDTO;
import com.amacom.amacom.model.PersonSituationHasAlarmSigns;

@Mapper
public interface PersonSituationHasAlarmSignsMapper {

        PersonSituationHasAlarmSignsMapper INSTANCE = Mappers.getMapper(PersonSituationHasAlarmSignsMapper.class);

        PersonSituationHasAlarmSigns toPersonSituationHasAlarmSigns(
                        PersonSituationHasAlarmSignsDTO personSituationHasAlarmSignsDTO);

        @Mapping(target = "personSituationId", source = "personSituation.id")
        @Mapping(target = "alarmSignId", source = "alarmSign.id")
        PersonSituationHasAlarmSignsDTO toPersonSituationHasAlarmSignsDTO(
                        PersonSituationHasAlarmSigns personSituationHasAlarmSigns);
}
