package com.amacom.amacom.service.interfaces;

import com.amacom.amacom.model.Genero;
import com.amacom.amacom.model.Params;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface IParamsService {

    Params findById(UUID id);

    Params create(Params params);

    Params update(Params params);

    void deleteById(UUID id);

    Params getEntityFromUUID(UUID uuid);


}
