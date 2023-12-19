package com.amacom.amacom.service.interfaces;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.amacom.amacom.model.Params;

@Service
public interface IParamsService {

    Params findById(UUID id);

    Params create(Params params);

    Params update(Params params);

    void deleteById(UUID id);

    Params getEntityFromUUID(UUID uuid);

}
