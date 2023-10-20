package com.amacom.amacom.service.interfaces;

import com.amacom.amacom.model.Genero;
import com.amacom.amacom.model.Result;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface IResultService {

    Result findById(UUID id);

    Result create(Result result);

    Result update(Result result);

    void deleteById(UUID id);

    Result getEntityFromUUID(UUID uuid);


}
