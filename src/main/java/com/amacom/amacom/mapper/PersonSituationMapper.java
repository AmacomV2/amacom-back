package com.amacom.amacom.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.amacom.amacom.dto.PersonSituationDTO;
import com.amacom.amacom.model.PersonSituation;
import com.amacom.amacom.model.PersonSituationHasFeelings;

@Mapper
public interface PersonSituationMapper {

    PersonSituationMapper INSTANCE = Mappers.getMapper(PersonSituationMapper.class);

    @Mapping(target = "feelings", ignore = true)
    // @Mapping(target = "feelings.personSituation.id", source = "id")
    PersonSituation toPersonSituation(PersonSituationDTO personSituationDTO);

    @Mapping(target = "personId", source = "person.id")
    @Mapping(target = "personName", source = "person.fullName")
    @Mapping(target = "createdById", source = "createdBy.id")
    @Mapping(target = "currentDiagnosis", source = "currentDiagnosis")
    @Mapping(target = "affectationDegree", source = "affectationDegree")
    @Mapping(target = "subjectId", source = "subject.id")
    @Mapping(target = "subjectName", source = "subject.name")
    PersonSituationDTO toPersonSituationDTO(PersonSituation personSituation);

    /**
     * Mapper para enviar los feelings de una situacion al DTO de situacion
     *
     * @param feelingsList
     * @return
     */
    static List<UUID> toFeelingsDTO(List<PersonSituationHasFeelings> feelingsList) {
        return feelingsList.stream().map((val) -> {
            return val.getFeelings().getId();
        }).collect(Collectors.toList());
    }

    static Map<String, String> getSortKeys() {
        Map<String, String> keysToSort = new HashMap<>();
        keysToSort.put("behavior", "behavior");
        keysToSort.put("affectationDegree", "affectationDegree");
        keysToSort.put("fistThought", "fistThought");
        keysToSort.put("description", "description");
        return keysToSort;
    }
}
