package com.amacom.amacom.service.interfaces;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.amacom.amacom.model.Gender;

@Service
public interface IGenderService {

    List<Gender> getAll();

    Gender findById(UUID id);

    Gender create(Gender gender);

    Gender update(Gender gender);

    void deleteById(UUID id);

    Gender getEntityFromUUID(UUID uuid);
}
