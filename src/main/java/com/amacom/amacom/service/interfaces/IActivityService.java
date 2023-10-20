package com.amacom.amacom.service.interfaces;

import com.amacom.amacom.model.Activity;
import com.amacom.amacom.model.Genero;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface IActivityService {

    Activity findById(UUID id);

    Activity create(Activity activity);

    Activity update(Activity activity);

    void deleteById(UUID id);

    Activity getEntityFromUUID(UUID uuid);


}
