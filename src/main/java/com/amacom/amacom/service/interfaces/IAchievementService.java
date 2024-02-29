package com.amacom.amacom.service.interfaces;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.amacom.amacom.model.Achievement;

@Service
public interface IAchievementService {

    Achievement findById(UUID id);

    Page<Achievement> findAchievement(UUID subjectId, String query, Pageable pageable);

    Page<Achievement> findNotAchieved(UUID subjectId, UUID personId, String query, Pageable pageable);

    Achievement create(Achievement achievement);

    Achievement update(Achievement achievement);

    void deleteById(UUID id);

    Achievement getEntityFromUUID(UUID uuid);
}
