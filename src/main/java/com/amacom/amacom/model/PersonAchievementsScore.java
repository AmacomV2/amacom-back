package com.amacom.amacom.model;

import java.util.UUID;

public interface PersonAchievementsScore {
    public String getSubjectName();

    public UUID getSubjectId();

    public Long getAchievementCount();

    public Long getPersonAchievementCount();
}
