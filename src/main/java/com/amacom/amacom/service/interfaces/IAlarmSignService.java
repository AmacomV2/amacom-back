package com.amacom.amacom.service.interfaces;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.amacom.amacom.model.AlarmSign;

@Service
public interface IAlarmSignService {

    AlarmSign findById(UUID id);

    AlarmSign create(AlarmSign alarmSign);

    AlarmSign update(AlarmSign alarmSign);

    void deleteById(UUID id);

    AlarmSign getEntityFromUUID(UUID uuid);

}
