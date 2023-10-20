package com.amacom.amacom.service.interfaces;

import com.amacom.amacom.model.Genero;
import com.amacom.amacom.model.ResultHasIndicator;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface IResultHasIndicatorService {

    ResultHasIndicator findById(UUID id);

    ResultHasIndicator create(ResultHasIndicator resultHasIndicator);

    ResultHasIndicator update(ResultHasIndicator resultHasIndicator);

    void deleteById(UUID id);

    ResultHasIndicator getEntityFromUUID(UUID uuid);


}
