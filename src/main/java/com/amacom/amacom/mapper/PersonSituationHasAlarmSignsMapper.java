package com.amacom.amacom.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.amacom.amacom.dto.PersonSituationHasAlarmSignsDTO;
import com.amacom.amacom.model.PersonSituationHasAlarmSigns;

import java.util.HashMap;
import java.util.Map;

@Mapper
public interface PersonSituationHasAlarmSignsMapper {

        PersonSituationHasAlarmSignsMapper INSTANCE = Mappers.getMapper(PersonSituationHasAlarmSignsMapper.class);

        PersonSituationHasAlarmSigns toPersonSituationHasAlarmSigns(
                        PersonSituationHasAlarmSignsDTO personSituationHasAlarmSignsDTO);

        @Mapping(target = "personSituationId", source = "personSituation.id")
        @Mapping(target = "alarmSignId", source = "alarmSign.id")
        @Mapping(target = "alarmSignName", source = "alarmSign.name")
        @Mapping(target = "alarmSignDescription", source = "alarmSign.description")
        PersonSituationHasAlarmSignsDTO toPersonSituationHasAlarmSignsDTO(
                        PersonSituationHasAlarmSigns personSituationHasAlarmSigns);

        static Map<String, String> getSortKeys() {
                Map<String, String> keysToSort = new HashMap<>();
                keysToSort.put("name", "name");
                keysToSort.put("description", "description");
                return keysToSort;
        }
}
