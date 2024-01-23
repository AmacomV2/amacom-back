package com.amacom.amacom.service.interfaces;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.amacom.amacom.model.Activity;

@Service
public interface IActivityService {

    Activity findById(UUID id);

    Page<Activity> search(String query, Pageable pageable);

    Activity create(Activity activity);

    Activity update(Activity activity);

    void deleteById(UUID id);

    Activity getEntityFromUUID(UUID uuid);

}
