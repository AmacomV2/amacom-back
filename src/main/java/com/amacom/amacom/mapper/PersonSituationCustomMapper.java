package com.amacom.amacom.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import com.amacom.amacom.dto.PersonSituationDTO;
import com.amacom.amacom.model.PersonSituation;
import com.amacom.amacom.model.PersonSituationHasFeelings;

public class PersonSituationCustomMapper {

    public static PersonSituationCustomMapper INSTANCE = new PersonSituationCustomMapper();

    public PersonSituation toPersonSituation(PersonSituationDTO personSituationDTO) {
        return PersonSituationMapper.INSTANCE.toPersonSituation(personSituationDTO);
    }

    public PersonSituationDTO toPersonSituationDTO(PersonSituation personSituation) {
        var personSituationDTO = PersonSituationMapper.INSTANCE.toPersonSituationDTO(personSituation);

        personSituationDTO.setFeelingsData(personSituation.getFeelings().stream()
                .map(f -> FeelingsMapper.INSTANCE.toFeelingsDTO(f.getFeelings())).collect(Collectors.toList()));
        personSituationDTO.setAlarmSignsData(personSituation.getAlarmSigns().stream()
                .map(f -> AlarmSignMapper.INSTANCE.toAlarmSignDTO(f.getAlarmSign())).collect(Collectors.toList()));
        personSituationDTO
                .setCurrentDiagnosis(DiagnosisMapper.INSTANCE.toDiagnosisDTO(personSituation.getCurrentDiagnosis()));
        return personSituationDTO;
    }

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
