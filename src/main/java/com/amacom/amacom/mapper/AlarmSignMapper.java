package com.amacom.amacom.mapper;

import java.util.HashMap;
import java.util.Map;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.amacom.amacom.dto.AlarmSignDTO;
import com.amacom.amacom.model.AlarmSign;

@Mapper
public interface AlarmSignMapper {

    AlarmSignMapper INSTANCE = Mappers.getMapper(AlarmSignMapper.class);

    AlarmSign toAlarmSign(AlarmSignDTO alarmSignDTO);

    AlarmSignDTO toAlarmSignDTO(AlarmSign alarmSign);

    static Map<String, String> getSortKeys() {
        Map<String, String> keysToSort = new HashMap<>();
        keysToSort.put("name", "name");
        keysToSort.put("description", "description");
        return keysToSort;
    }
}
