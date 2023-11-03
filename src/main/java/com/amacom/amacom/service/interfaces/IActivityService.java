package com.amacom.amacom.service.interfaces;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.amacom.amacom.model.Activity;

@Service
public interface IActivityService {

    Activity findById(UUID id);

    Activity create(Activity activity);

    Activity update(Activity activity);

    void deleteById(UUID id);

    Activity getEntityFromUUID(UUID uuid);

}
