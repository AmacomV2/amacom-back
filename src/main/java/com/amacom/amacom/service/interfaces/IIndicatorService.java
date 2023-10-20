package com.amacom.amacom.service.interfaces;

import com.amacom.amacom.model.Genero;
import com.amacom.amacom.model.Indicator;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface IIndicatorService {

    Indicator findById(UUID id);

    Indicator create(Indicator indicator);

    Indicator update(Indicator indicator);

    void deleteById(UUID id);

    Indicator getEntityFromUUID(UUID uuid);


}
