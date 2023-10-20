package com.amacom.amacom.service.interfaces;

import com.amacom.amacom.model.Achievement;
import com.amacom.amacom.model.Genero;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface IAchievementService {

    Achievement findById(UUID id);

    Achievement create(Achievement achievement);

    Achievement update(Achievement achievement);

    void deleteById(UUID id);

    Achievement getEntityFromUUID(UUID uuid);
}
