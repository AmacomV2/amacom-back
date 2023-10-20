package com.amacom.amacom.service.interfaces;

import com.amacom.amacom.model.Feelings;
import com.amacom.amacom.model.Genero;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface IFeelingsService {

    Feelings findById(UUID id);

    Feelings create(Feelings feelings);

    Feelings update(Feelings feelings);

    void deleteById(UUID id);

    Feelings getEntityFromUUID(UUID uuid);


}
