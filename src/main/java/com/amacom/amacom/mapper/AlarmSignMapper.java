package com.amacom.amacom.mapper;

import com.amacom.amacom.dto.AlarmSignDTO;
import com.amacom.amacom.model.AlarmSign;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AlarmSignMapper {

    AlarmSignMapper INSTANCE = Mappers.getMapper(AlarmSignMapper.class);

    AlarmSign toAlarmSign(AlarmSignDTO alarmSignDTO);

    AlarmSignDTO toAlarmSignDTO(AlarmSign alarmSign);
}
