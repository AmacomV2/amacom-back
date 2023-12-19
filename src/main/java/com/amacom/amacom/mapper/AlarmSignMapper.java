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

    static Map<String, String> getClavesToSort() {
        Map<String, String> clavesToSort = new HashMap<>();
        clavesToSort.put("name", "name");
        clavesToSort.put("description", "description");
        return clavesToSort;
    }
}
