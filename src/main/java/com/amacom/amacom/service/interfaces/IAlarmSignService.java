package com.amacom.amacom.service.interfaces;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import com.amacom.amacom.model.AlarmSign;
import com.amacom.amacom.model.EAlarmSignType;

@Service
public interface IAlarmSignService {

    AlarmSign findById(UUID id);

    AlarmSign create(AlarmSign alarmSign);

    AlarmSign update(AlarmSign alarmSign);

    void deleteById(UUID id);

    AlarmSign getEntityFromUUID(UUID uuid);

    Page<AlarmSign> findAlarmSign(@Nullable EAlarmSignType type, String query, Pageable pageable);

}
