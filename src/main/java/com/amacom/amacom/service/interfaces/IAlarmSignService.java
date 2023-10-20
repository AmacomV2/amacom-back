package com.amacom.amacom.service.interfaces;

import com.amacom.amacom.model.AlarmSign;
import com.amacom.amacom.model.Genero;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface IAlarmSignService {

    AlarmSign findById(UUID id);

    AlarmSign create(AlarmSign alarmSign);

    AlarmSign update(AlarmSign alarmSign);

    void deleteById(UUID id);

    AlarmSign getEntityFromUUID(UUID uuid);


}
