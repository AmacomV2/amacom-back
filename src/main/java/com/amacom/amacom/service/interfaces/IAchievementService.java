package com.amacom.amacom.service.interfaces;

import com.amacom.amacom.model.Achievement;
import com.amacom.amacom.model.Genero;
import com.amacom.amacom.model.PersonBabys;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface IAchievementService {

    Achievement findById(UUID id);

    Page<Achievement> findAchievement(UUID idSubject, String query, Pageable pageable);

    Achievement create(Achievement achievement);

    Achievement update(Achievement achievement);

    void deleteById(UUID id);

    Achievement getEntityFromUUID(UUID uuid);
}
